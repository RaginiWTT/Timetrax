package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "Resource", uniqueConstraints = {
        @UniqueConstraint(columnNames = "emailId")
})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ResourceId")
    private Integer resourceId;

    @Column(name = "EmailId", length = 200, nullable = false, unique = true)
    private String emailId;

    @Column(name = "Password", length = 20, nullable = false)
    private String password;

    @Column(name = "FirstName", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 100, nullable = false)
    private String lastName;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "AddressLine1", length = 100)
    private String addressLine1;

    @Column(name = "AddressLine2", length = 100)
    private String addressLine2;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "State", length = 100)
    private String state;

    @Column(name = "Zipcode", length = 20)
    private String zipcode;

    @Column(name = "Country", length = 100)
    private String country;

  
    
    @Column(name = "Role", nullable = false)
    private int role;

    @Column(name = "Active", nullable = false)
    private Boolean active;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedDateTime")
    private LocalDateTime createdDateTime;

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", emailId=" + emailId + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", country=" + country + ", role=" + role + ", active=" + active + ", createdBy="
				+ createdBy + ", createdDateTime=" + createdDateTime + "]";
	}

	public Resource( String emailId, String password, String firstName, String lastName,
			String phoneNumber, String addressLine1, String addressLine2, String city, String state, String zipcode,
			String country, int role, Boolean active, Integer createdBy, LocalDateTime createdDateTime) {
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
		this.createdDateTime = createdDateTime;
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

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

    // Getters and Setters (You can generate them using Lombok or manually)
	
	
    
    
}

