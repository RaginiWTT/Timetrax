//package com.wtt.TimetraxRestApis.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wtt.TimetraxRestApis.dto.ResourceDTO;
//import com.wtt.TimetraxRestApis.entity.Customer;
//import com.wtt.TimetraxRestApis.entity.Resource;
//import com.wtt.TimetraxRestApis.service.ResourceService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@Tag(name = "Rest APIs for Resource", description = "These APIs are designed for managing the resources in the Timetrax application")
//@RestController
//@RequestMapping("/api/resource")
//public class ResourceController {
//	
//	
//	private ResourceService resourceService;
//	
//	  @Autowired
//	    public ResourceController(ResourceService resourceService) {
//	        this.resourceService = resourceService;
//	    }
//	
//	//handler method for creating a resource 
//	  @Operation(summary = "Create a new resource", description = "This API creates a new resource in the Timetrax application.")
//	  @ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
//	@PostMapping("/add")
//	public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
//		System.out.println("Creating resource: " + resourceDTO);
//		ResourceDTO savedResource = resourceService.createResource(resourceDTO);
//		return  new ResponseEntity<>(savedResource,HttpStatus.CREATED);
//	}
//	
//   
//	
//	  @Operation(summary = "Update an existing resource", description = "This API updates the information of existing resource in the Timetrax application.")
//	  @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@PutMapping("/update/{id}")
//	public ResponseEntity<ResourceDTO> updateCustomer(@PathVariable("id") int id, @RequestBody ResourceDTO resourceDTO)
//	{
//		//resourceDTO.setResourceId(id); // Set the ID of the Resource to be updated
//		ResourceDTO updatedResourceDTO = resourceService.updateResource(resourceDTO,id);
//		return new ResponseEntity<>(updatedResourceDTO, HttpStatus.OK);
//	}
//	
////	@PostMapping("/login/{emailId}/{password}")
////	public ResponseEntity<Resource> loginResourceByEmailId_Password(@PathVariable("emailId") String emailId,
////			@PathVariable("password") String password) {
////		Resource resource = resourceService.loginResourceByEmailId_Password(emailId, password);
////		if (resource != null) {
////			return new ResponseEntity<>(resource, HttpStatus.OK);
////		} else {
////			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////		}
////	}
//	
//	  @Operation(summary = "Login resource by email and password", description = "This API allows a resource to log in using their email ID and password.")
//	  @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@PostMapping("/login")
//	public ResponseEntity<ResourceDTO> loginResourceByEmailId_Password(@RequestParam("emailId") String emailId,
//			@RequestParam("password") String password) {
//		ResourceDTO resourceDTO = resourceService.loginResourceByEmailId_Password(emailId, password);
//		if (resourceDTO != null) {
//			return new ResponseEntity<>(resourceDTO, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//	}
//	
//	// Get Resource By Id method
//	  @Operation(summary = "Get resource by ID", description = "This API retrieves a resource by its ID.")
//	  @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@GetMapping("/{resourceId}")
//	public ResponseEntity<Resource> getResourceById(@PathVariable("resourceId") Integer resourceId) {
//		Resource resource = resourceService.getResourceById(resourceId);
//		return new ResponseEntity<>(resource, HttpStatus.OK);
//	}
//	  
//	  
//	@Operation(summary = "Get all resources", description = "This API retrieves all resources in the Timetrax application.")
//	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@GetMapping("/all")
//	public ResponseEntity<List<Resource>> getAllResources() {
//		List<Resource> resources = resourceService.getAllResources();
//		if (resources != null && !resources.isEmpty()) {
//			return new ResponseEntity<>(resources, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//}


package com.wtt.TimetraxRestApis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.service.ResourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rest APIs for Resource", description = "These APIs are designed for managing the resources in the Timetrax application")
@RestController
@RequestMapping("/api/resource")
public class ResourceController {
	
	private ResourceService resourceService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	// Handler method for creating a resource (Registration - Public)
	@Operation(summary = "Create a new resource", description = "This API creates a new resource in the Timetrax application.")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
	@PostMapping("/add")
	public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
		System.out.println("Creating resource: " + resourceDTO);
		
		// Encode password before saving
		if (resourceDTO.getPassword() != null) {
			resourceDTO.setPassword(passwordEncoder.encode(resourceDTO.getPassword()));
		}
		
		ResourceDTO savedResource = resourceService.createResource(resourceDTO);
		return new ResponseEntity<>(savedResource, HttpStatus.CREATED);
	}
	
	// Update resource - Only authenticated users can update (Admin or own profile)
	@Operation(summary = "Update an existing resource", 
			   description = "This API updates the information of existing resource in the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or @resourceService.isResourceOwner(authentication.name, #id)")
	public ResponseEntity<ResourceDTO> updateResource(@PathVariable("id") int id, @RequestBody ResourceDTO resourceDTO) {
		// Encode password if provided
		if (resourceDTO.getPassword() != null && !resourceDTO.getPassword().isEmpty()) {
			resourceDTO.setPassword(passwordEncoder.encode(resourceDTO.getPassword()));
		}
		
		ResourceDTO updatedResourceDTO = resourceService.updateResource(resourceDTO, id);
		return new ResponseEntity<>(updatedResourceDTO, HttpStatus.OK);
	}
	
	// Deprecated login method - Use /api/auth/login instead
	@Operation(summary = "Login resource by email and password (Deprecated)", 
			   description = "This API is deprecated. Use /api/auth/login instead for JWT authentication.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@ApiResponse(responseCode = "410", description = "This endpoint is deprecated")
	@PostMapping("/login")
	@Deprecated
	public ResponseEntity<?> loginResourceByEmailId_Password(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password) {
		
		return ResponseEntity.status(HttpStatus.GONE)
				.body(new DeprecationResponse("This endpoint is deprecated. Please use /api/auth/login for JWT authentication."));
	}
	
	// Get Resource By Id - Only authenticated users
	@Operation(summary = "Get resource by ID", description = "This API retrieves a resource by its ID.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{resourceId}")
	@PreAuthorize("hasRole('ADMIN') or @resourceService.isResourceOwner(authentication.name, #resourceId)")
	public ResponseEntity<Resource> getResourceById(@PathVariable("resourceId") Integer resourceId) {
		Resource resource = resourceService.getResourceById(resourceId);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	// Get current user profile
	@Operation(summary = "Get current user profile", description = "This API retrieves the current authenticated user's profile.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/profile")
	public ResponseEntity<Resource> getCurrentUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailId = authentication.getName();
		
		Resource resource = resourceService.getResourceByEmailId(emailId);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	// Get all resources - Admin only
	@Operation(summary = "Get all resources", description = "This API retrieves all resources in the Timetrax application. Admin access required.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Resource>> getAllResources() {
		List<Resource> resources = resourceService.getAllResources();
		if (resources != null && !resources.isEmpty()) {
			return new ResponseEntity<>(resources, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	// Get user resources - For regular users to see limited data
	@Operation(summary = "Get user accessible resources", description = "This API retrieves resources accessible to the current user.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/user-resources")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Resource>> getUserAccessibleResources() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// If admin, return all resources, if user, return limited data
		if (authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
			List<Resource> resources = resourceService.getAllResources();
			return new ResponseEntity<>(resources, HttpStatus.OK);
		} else {
			// For regular users, return only their own profile
			String emailId = authentication.getName();
			Resource resource = resourceService.getResourceByEmailId(emailId);
			return new ResponseEntity<>(List.of(resource), HttpStatus.OK);
		}
	}
	
	// Response class for deprecation notice
	public static class DeprecationResponse {
		private String message;
		private long timestamp;
		
		public DeprecationResponse(String message) {
			this.message = message;
			this.timestamp = System.currentTimeMillis();
		}
		
		public String getMessage() { return message; }
		public void setMessage(String message) { this.message = message; }
		public long getTimestamp() { return timestamp; }
		public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
	}
}
