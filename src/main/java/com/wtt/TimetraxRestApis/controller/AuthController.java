package com.wtt.TimetraxRestApis.controller;


import com.wtt.TimetraxRestApis.dto.LoginRequest;
import com.wtt.TimetraxRestApis.dto.LoginResponse;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;
import com.wtt.TimetraxRestApis.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication APIs", description = "APIs for user authentication and JWT token management")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ResourceRepo resourceRepo;

    @Operation(summary = "Authenticate user and generate JWT token", 
               description = "Authenticates user credentials and returns JWT token for subsequent API calls")
    @ApiResponse(responseCode = "200", description = "Authentication successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmailId(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details
            Resource resource = resourceRepo.findByEmailId(loginRequest.getEmailId());
            
            if (resource == null || !resource.getActive()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiErrorResponse("User account is inactive or not found"));
            }

            // Generate JWT token
            String jwt = jwtUtil.generateToken(
                resource.getEmailId(),
                resource.getResourceId(),
                resource.getRole()
            );

            // Create response
            LoginResponse response = new LoginResponse(
                jwt,
                "Bearer",
                resource.getResourceId(),
                resource.getEmailId(),
                resource.getFirstName(),
                resource.getLastName(),
                resource.getRole(),
                getRoleName(resource.getRole())
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiErrorResponse("Invalid email or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse("Authentication failed: " + e.getMessage()));
        }
    }

    @Operation(summary = "Validate JWT token", description = "Validates the provided JWT token")
    @ApiResponse(responseCode = "200", description = "Token is valid")
    @ApiResponse(responseCode = "401", description = "Token is invalid or expired")
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                String jwt = token.substring(7);
                
                if (jwtUtil.validateToken(jwt)) {
                    String emailId = jwtUtil.extractEmailId(jwt);
                    Integer resourceId = jwtUtil.extractResourceId(jwt);
                    Integer role = jwtUtil.extractRole(jwt);
                    
                    return ResponseEntity.ok(new TokenValidationResponse(
                        true, 
                        "Token is valid", 
                        emailId, 
                        resourceId, 
                        role,
                        getRoleName(role)
                    ));
                }
            }
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenValidationResponse(false, "Invalid or expired token", null, null, null, null));
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenValidationResponse(false, "Token validation failed", null, null, null, null));
        }
    }

    @Operation(summary = "Logout user", description = "Logout endpoint (client should discard the token)")
    @ApiResponse(responseCode = "200", description = "Logout successful")
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new ApiSuccessResponse("Logged out successfully"));
    }

    private String getRoleName(int roleId) {
        return switch (roleId) {
            case 1 -> "ADMIN";
            case 2 -> "USER";
            default -> "USER";
        };
    }

    // Response classes
    public static class ApiErrorResponse {
        private String message;
        private long timestamp;

        public ApiErrorResponse(String message) {
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }

    public static class ApiSuccessResponse {
        private String message;
        private long timestamp;

        public ApiSuccessResponse(String message) {
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }

    public static class TokenValidationResponse {
        private boolean valid;
        private String message;
        private String emailId;
        private Integer resourceId;
        private Integer role;
        private String roleName;

        public TokenValidationResponse(boolean valid, String message, String emailId, 
                                     Integer resourceId, Integer role, String roleName) {
            this.valid = valid;
            this.message = message;
            this.emailId = emailId;
            this.resourceId = resourceId;
            this.role = role;
            this.roleName = roleName;
        }

        // Getters and setters
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getEmailId() { return emailId; }
        public void setEmailId(String emailId) { this.emailId = emailId; }
        public Integer getResourceId() { return resourceId; }
        public void setResourceId(Integer resourceId) { this.resourceId = resourceId; }
        public Integer getRole() { return role; }
        public void setRole(Integer role) { this.role = role; }
        public String getRoleName() { return roleName; }
        public void setRoleName(String roleName) { this.roleName = roleName; }
    }
}
