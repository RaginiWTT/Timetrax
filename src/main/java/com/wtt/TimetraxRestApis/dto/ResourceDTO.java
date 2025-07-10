package com.wtt.TimetraxRestApis.dto;


public class ResourceDTO {
	    private String emailId;

	    private String password;

	    private String firstName;

	    private String lastName;

	    private String phoneNumber;

	    private String addressLine1;

	    private String addressLine2;

	    private String city;

	    private String state;

	    private String zipcode;

	    private String country;

	    private int role;

	    private Boolean active;

	    private Integer createdBy;
	    
	    private Integer modifiedBy;
	    
		public ResourceDTO() {
			// Default constructor
		}
		
		public ResourceDTO(String emailId, String password, String firstName, String lastName, String phoneNumber,
				String addressLine1, String addressLine2, String city, String state, String zipcode, String country,
				int role, Boolean active, Integer createdBy, Integer modifiedBy) {
			super();
			this.emailId = emailId;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.city = city;
			this.state = state;
			this.zipcode = zipcode;
			this.country = country;
			this.role = role;
			this.active = active;
			this.createdBy = createdBy;
			this.modifiedBy = modifiedBy;
		}



		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

//		public void setPassword(String password) {
//			this.password = password;
//		}

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

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getAddressLine1() {
			return addressLine1;
		}

		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}

		public String getAddressLine2() {
			return addressLine2;
		}

		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getRole() {
			return role;
		}

		public void setRole(int role) {
			this.role = role;
		}

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
		}

		public Integer getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}

		public Integer getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(Integer modifiedBy) {
			this.modifiedBy = modifiedBy;
		}
		
		
     
}
