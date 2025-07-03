package com.wtt.TimetraxRestApis.service;

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
	
	

}
