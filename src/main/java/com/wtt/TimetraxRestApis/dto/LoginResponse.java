package com.wtt.TimetraxRestApis.dto;


public class LoginResponse {
    private String accessToken;
    private String tokenType;
    private Integer resourceId;
    private String emailId;
    private String firstName;
    private String lastName;
    private Integer role;
    private String roleName;
    private long expiresIn;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, String tokenType, Integer resourceId, 
                        String emailId, String firstName, String lastName, 
                        Integer role, String roleName) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.resourceId = resourceId;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.roleName = roleName;
        this.expiresIn = 86400; // 24 hours in seconds
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
