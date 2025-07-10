package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;

public interface CustomerService {
	public CustomerDTO createCustomer(CustomerDTO customer);
	
	public CustomerDTO updateCustomer(CustomerDTO customerDTO,int id);

	public Customer getCustomerById(Integer customerId);

}
