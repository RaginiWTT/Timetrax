//package com.wtt.TimetraxRestApis.service;
//
//import java.util.List;
//
//import com.wtt.TimetraxRestApis.dto.CustomerDTO;
//import com.wtt.TimetraxRestApis.entity.Customer;
//
//public interface CustomerService {
//	public CustomerDTO createCustomer(CustomerDTO customer);
//	
//	public CustomerDTO updateCustomer(CustomerDTO customerDTO,int id);
//
//	public Customer getCustomerById(Integer customerId);
//	public List<Customer> getAllCustomers();
//	
//
//}


package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;

public interface CustomerService {
	
	public CustomerDTO createCustomer(CustomerDTO customerDTO);
	
	public CustomerDTO updateCustomer(CustomerDTO customerDTO, int customerId);

	public Customer getCustomerById(Integer customerId);
	
	public List<Customer> getAllCustomers();
	
	public List<Customer> getActiveCustomers();
	
	public List<Customer> searchCustomersByName(String customerName);
	
	public long getTotalCustomersCount();
	
	public long getActiveCustomersCount();
	
	public boolean deactivateCustomer(Integer customerId);
	
	public boolean activateCustomer(Integer customerId);
	
	public List<Customer> advancedSearchCustomers(String searchTerm);
	
	public List<Customer> getCustomersByCity(String city);
	
	public List<Customer> getCustomersByState(String state);
	
	public List<Customer> getCustomersByCountry(String country);
	
	public List<Customer> getCustomersByCreatedBy(Integer createdBy);
}
