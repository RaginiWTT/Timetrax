package com.wtt.TimetraxRestApis.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CutomerController {

	// Add methods to handle customer-related requests here
	// For example, you can implement methods to create, update, delete, and
	// retrieve customers
	
//	@Autowired
//	ModelMapper modelMapper;
	private CustomerService customerService;
	
	@Autowired
	 public CutomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/add")
	 public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO
	 customerDTO) {
		CustomerDTO createdCustomer=	customerService.createCustomer(customerDTO);
	 return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	 }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO)
	{
		//customer.setCustomerId(id); // Set the ID of the customer to be updated
		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO,id);
		return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
		
	}
	
	// getCustomer By Id method
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		//CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	
	

}
