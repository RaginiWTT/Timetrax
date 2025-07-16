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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rest APIs for Customer", description = "These APIs are designed for managing the customers in the Timetrax application")
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

	@Operation(summary = "Create a new customer", description = "This API creates a new customer in the Timetrax application.")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
	@PostMapping("/add")
	 public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO
	 customerDTO) {
		CustomerDTO createdCustomer=	customerService.createCustomer(customerDTO);
	 return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	 }
	
	
	@Operation(summary = "Update an existing customer", description = "This API updates the information of existing customer in the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO)
	{
		//customer.setCustomerId(id); // Set the ID of the customer to be updated
		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO,id);
		return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
		
	}
	
	@Operation(summary = "Get a customer by ID", description = "This API retrieves a customer by its ID from the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	// getCustomer By Id method
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		//CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get all customers", description = "This API retrieves all customers from the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	
	

}
