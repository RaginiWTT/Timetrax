package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CutomerController {

	// Add methods to handle customer-related requests here
	// For example, you can implement methods to create, update, delete, and
	// retrieve customers
     
	private CustomerService customerService;
	
	@Autowired
	 public CutomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/add")
	 public ResponseEntity<Customer> createCustomer(@RequestBody Customer
	 customer) {
	  Customer createdCustomer=	customerService.createCustomer(customer);
	 return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	 }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer)
	{
		customer.setCustomerId(id); // Set the ID of the customer to be updated
		Customer updatedCustomer = customerService.updateCustomer(customer);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		
	}

}
