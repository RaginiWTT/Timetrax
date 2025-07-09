package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.entity.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);

	public Customer getCustomerById(Integer customerId);

}
