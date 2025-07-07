package com.wtt.TimetraxRestApis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.repository.CustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	// Implement the methods defined in CustomerService interface
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
	  Customer existingCustomer = customerRepo.findById(customer.getCustomerId()).get();
	          if (existingCustomer != null) {
	        	          existingCustomer.setCustomerName(customer.getCustomerName());
	        	          	existingCustomer.setEmail(customer.getEmail());
	        	          	existingCustomer.setPhoneNumber(customer.getPhoneNumber());
	        	          		existingCustomer.setFaxNumber(customer.getFaxNumber());
	        	          		existingCustomer.setAddressLine1(customer.getAddressLine1());
	        	          		existingCustomer.setAddressLine2(customer.getAddressLine2());
	        	          		existingCustomer.setCity(customer.getCity());
	        	          		existingCustomer.setState(customer.getState());
	        	          			existingCustomer.setZipcode(customer.getZipcode());
	        	          			existingCustomer.setCountry(customer.getCountry());
	        	          			existingCustomer.setActive(customer.getActive());
	        	          			existingCustomer.setModifiedBy(customer.getModifiedBy());
	        	          			Customer updateCustomer=customerRepo.save(existingCustomer);
	        	          			
	        	          			return updateCustomer;
	          }
		return null;
	}
	
	

}
