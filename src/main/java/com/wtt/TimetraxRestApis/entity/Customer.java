package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer", uniqueConstraints = {
    @UniqueConstraint(columnNames = "CustomerName")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(length = 200, nullable = false, unique = true, name = "CustomerName")
    private String customerName;

    @Column(length = 100,name = "Email")
    private String email;

    @Column(length = 20, name = "PhoneNumber")
    private String phoneNumber;

    @Column(length = 20, name = "FaxNumber")
    private String faxNumber;

    @Column(length = 100, name = "AddressLine1")
    private String addressLine1;

    @Column(length = 100, name = "AddressLine2")
    private String addressLine2;

    @Column(length = 100, name = "City")
    private String city;

    @Column(length = 100, name = "State")
    private String state;

    @Column(length = 20, name = "Zipcode")
    private String zipcode;

    @Column(length = 100, name = "Country")
    private String country;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "CreatedBy")
    private Integer createdBy;
    
    
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;
    
	public Customer() {
		// Default constructor
	}

    // Getters and Setters

   
    public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Customer( String customerName, String email, String phoneNumber, String faxNumber,
			String addressLine1, String addressLine2, String city, String state, String zipcode, String country,
			Boolean active, Integer createdBy) {
		super();
	
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.active = active;
		this.createdBy = createdBy;
	}

	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

	@Override
	public String toString() {
		return "Customer [ customerName=" + customerName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", country=" + country + ", active=" + active + ", createdBy=" + createdBy + "]";
	}
    
    
}
