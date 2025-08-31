//package com.wtt.TimetraxRestApis.controller;
//
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wtt.TimetraxRestApis.dto.CustomerDTO;
//import com.wtt.TimetraxRestApis.entity.Customer;
//import com.wtt.TimetraxRestApis.service.CustomerService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@Tag(name = "Rest APIs for Customer", description = "These APIs are designed for managing the customers in the Timetrax application")
//@RestController
//@RequestMapping("/api/customer")
//public class CutomerController {
//
//	// Add methods to handle customer-related requests here
//	// For example, you can implement methods to create, update, delete, and
//	// retrieve customers
//	
////	@Autowired
////	ModelMapper modelMapper;
//	private CustomerService customerService;
//	
//	@Autowired
//	 public CutomerController(CustomerService customerService) {
//		super();
//		this.customerService = customerService;
//	}
//
//	@Operation(summary = "Create a new customer", description = "This API creates a new customer in the Timetrax application.")
//	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
//	@PostMapping("/add")
//	 public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO
//	 customerDTO) {
//		CustomerDTO createdCustomer=	customerService.createCustomer(customerDTO);
//	 return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//	 }
//	
//	
//	@Operation(summary = "Update an existing customer", description = "This API updates the information of existing customer in the Timetrax application.")
//	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@PutMapping("/update/{id}")
//	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO)
//	{
//		//customer.setCustomerId(id); // Set the ID of the customer to be updated
//		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO,id);
//		return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
//		
//	}
//	
//	@Operation(summary = "Get a customer by ID", description = "This API retrieves a customer by its ID from the Timetrax application.")
//	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	// getCustomer By Id method
//	@GetMapping("/{customerId}")
//	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) {
//		Customer customer = customerService.getCustomerById(customerId);
//		//CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
//		return new ResponseEntity<>(customer, HttpStatus.OK);
//	}
//	
//	
//	@Operation(summary = "Get all customers", description = "This API retrieves all customers from the Timetrax application.")
//	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
//	@GetMapping("/all")
//	public ResponseEntity<List<Customer>> getAllCustomers() {
//		return ResponseEntity.ok(customerService.getAllCustomers());
//	}
//	
//	
//	
//
//}



package com.wtt.TimetraxRestApis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.service.CustomerService;
import com.wtt.TimetraxRestApis.service.ResourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rest APIs for Customer", description = "These APIs are designed for managing the customers in the Timetrax application")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	// Create Customer - Admin only
	@Operation(summary = "Create a new customer", 
			   description = "This API creates a new customer in the Timetrax application. Only administrators can create customers.")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		// Get current authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailId = authentication.getName();
		Resource currentUser = resourceService.getResourceByEmailId(emailId);
		
		// Set createdBy to current user's ID
		customerDTO.setCreatedBy(currentUser.getResourceId());
		
		CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}

	// Update Customer - Admin only
	@Operation(summary = "Update an existing customer", 
			   description = "This API updates the information of existing customer in the Timetrax application. Only administrators can update customers.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
		// Get current authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailId = authentication.getName();
		Resource currentUser = resourceService.getResourceByEmailId(emailId);
		
		// Set modifiedBy to current user's ID
		customerDTO.setModifiedBy(currentUser.getResourceId());
		
		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO, id);
		return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
	}

	// Get Customer by ID - Admin and Users can view
	@Operation(summary = "Get a customer by ID", 
			   description = "This API retrieves a customer by its ID from the Timetrax application. Accessible to authenticated users.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{customerId}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Get All Customers - Different access levels
	@Operation(summary = "Get all customers", 
			   description = "This API retrieves all customers from the Timetrax application. Full access for admins, limited for users.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// Check if user is admin
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
		
		List<Customer> customers = customerService.getAllCustomers();
		
		if (!isAdmin) {
			// For regular users, you might want to filter or limit the data
			// For now, returning all but you can modify this logic based on your requirements
			// For example, only return active customers for non-admin users
			customers = customerService.getActiveCustomers();
		}
		
		return ResponseEntity.ok(customers);
	}

	// Get Active Customers - For all authenticated users
	@Operation(summary = "Get all active customers", 
			   description = "This API retrieves all active customers from the Timetrax application. Accessible to all authenticated users.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/active")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Customer>> getActiveCustomers() {
		List<Customer> activeCustomers = customerService.getActiveCustomers();
		return ResponseEntity.ok(activeCustomers);
	}

	// Get Customers Count - Admin only
	@Operation(summary = "Get customers count", 
			   description = "This API returns the total count of customers. Only accessible to administrators.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/count")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerStatsResponse> getCustomersCount() {
		long totalCustomers = customerService.getTotalCustomersCount();
		long activeCustomers = customerService.getActiveCustomersCount();
		long inactiveCustomers = totalCustomers - activeCustomers;
		
		CustomerStatsResponse stats = new CustomerStatsResponse(totalCustomers, activeCustomers, inactiveCustomers);
		return ResponseEntity.ok(stats);
	}

	// Search Customers - Admin and Users
	@Operation(summary = "Search customers by name", 
			   description = "This API searches customers by name pattern. Accessible to authenticated users.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/search/{customerName}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Customer>> searchCustomersByName(@PathVariable("customerName") String customerName) {
		List<Customer> customers = customerService.searchCustomersByName(customerName);
		return ResponseEntity.ok(customers);
	}

	// Response class for customer statistics
	public static class CustomerStatsResponse {
		private long totalCustomers;
		private long activeCustomers;
		private long inactiveCustomers;

		public CustomerStatsResponse(long totalCustomers, long activeCustomers, long inactiveCustomers) {
			this.totalCustomers = totalCustomers;
			this.activeCustomers = activeCustomers;
			this.inactiveCustomers = inactiveCustomers;
		}

		public long getTotalCustomers() { return totalCustomers; }
		public void setTotalCustomers(long totalCustomers) { this.totalCustomers = totalCustomers; }
		public long getActiveCustomers() { return activeCustomers; }
		public void setActiveCustomers(long activeCustomers) { this.activeCustomers = activeCustomers; }
		public long getInactiveCustomers() { return inactiveCustomers; }
		public void setInactiveCustomers(long inactiveCustomers) { this.inactiveCustomers = inactiveCustomers; }
	}
}
