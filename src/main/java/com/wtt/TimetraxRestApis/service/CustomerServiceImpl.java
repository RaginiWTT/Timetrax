//package com.wtt.TimetraxRestApis.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.wtt.TimetraxRestApis.dto.CustomerDTO;
//import com.wtt.TimetraxRestApis.entity.Customer;
//import com.wtt.TimetraxRestApis.exception.CustomerAlreadyExistsException;
//import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
//import com.wtt.TimetraxRestApis.repository.CustomerRepo;
//
//import lombok.AllArgsConstructor;
//
//@Service
//@AllArgsConstructor
//public class CustomerServiceImpl implements CustomerService {
//	// Implement the methods defined in CustomerService interface
//
//	@Autowired
//	private CustomerRepo customerRepo;
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	@Override
//	public CustomerDTO createCustomer(CustomerDTO customerDto) {
//		// this matching strategy will force ModelMapper to map only exact matches,
//		// avoiding type confusion
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//		// Convert CustomerDTO to Customer entity
//		Customer customer = modelMapper.map(customerDto, Customer.class);
//		// Check if a customer with the same name already exists
////		Optional<Customer> existingCustomer = customerRepo.findByCustomerName(customer.getCustomerName());
//		Customer existingCustomer = customerRepo.findByCustomerName(customer.getCustomerName());
//
//		if (existingCustomer != null) {
//			// If a customer with the same name already exists, return null or throw an
//			// exception
//			throw new CustomerAlreadyExistsException(
//					"Customer already exists with name: " + customer.getCustomerName());
//		}
//		Customer savedCustomer = customerRepo.save(customer);
//
//		CustomerDTO savedCustomerDto = modelMapper.map(savedCustomer, CustomerDTO.class);
//		return savedCustomerDto; // Return the saved CustomerDTO;
//	}
//
//	@Override
//	public CustomerDTO updateCustomer(CustomerDTO customer, int id) {
//		Customer existingCustomer = customerRepo.findById(id).orElseThrow(() 
//				-> new ResourceNotFound("Customer","CustomerId", id));
//		
//		if (existingCustomer != null) {
//			
//			Customer customerByName = customerRepo.findByCustomerName(customer.getCustomerName());
//			if (customerByName != null && customerByName.getCustomerId()!= existingCustomer.getCustomerId()) {
//				throw new CustomerAlreadyExistsException(
//						"Customer already exists with name: " + customer.getCustomerName());
//			}
//			
//			existingCustomer.setCustomerName(customer.getCustomerName());
//			existingCustomer.setEmail(customer.getEmail());
//			existingCustomer.setPhoneNumber(customer.getPhoneNumber());
//			existingCustomer.setFaxNumber(customer.getFaxNumber());
//			existingCustomer.setAddressLine1(customer.getAddressLine1());
//			existingCustomer.setAddressLine2(customer.getAddressLine2());
//			existingCustomer.setCity(customer.getCity());
//			existingCustomer.setState(customer.getState());
//			existingCustomer.setZipcode(customer.getZipcode());
//			existingCustomer.setCountry(customer.getCountry());
//			existingCustomer.setActive(customer.getActive());
//			existingCustomer.setModifiedBy(customer.getModifiedBy());
//			Customer updateCustomer = customerRepo.save(existingCustomer);
//
//			// this matching strategy will force ModelMapper to map only exact matches,
//			// avoiding type confusion
//			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//			CustomerDTO updateCustomerDto = modelMapper.map(updateCustomer, CustomerDTO.class);
//			return updateCustomerDto;
//		}
//		return null;
//	}
//
//	@Override
//	public Customer getCustomerById(Integer customerId) {
//		// TODO Auto-generated method stub
//		Customer customer = customerRepo.findById(customerId)
//				.orElseThrow(() -> new ResourceNotFound("Customer", "CustomerId", customerId));
//		
//		
////		if (customer != null) {
////			return customer;
////		}
////		else {
////			throw new ResourceNotFound("Customer", "CustomerId", customerId);
////		}
//		
//		return customer;
//	}
//
//	@Override
//	public List<Customer> getAllCustomers() {
//		// TODO Auto-generated method stub
//		List<Customer> customers = customerRepo.findAll();
////		if (customers != null && !customers.isEmpty()) {
////			// Convert List<Customer> to List<CustomerDTO>
////			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
////			List<CustomerDTO> customerDtos = customers.stream()
////					.map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
////			return customerDtos;
////		}
//		return customers;
//	}
//	
//	
//
//}



package com.wtt.TimetraxRestApis.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.exception.CustomerAlreadyExistsException;
import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
import com.wtt.TimetraxRestApis.repository.CustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDto) {
		// this matching strategy will force ModelMapper to map only exact matches,
		// avoiding type confusion
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		// Convert CustomerDTO to Customer entity
		Customer customer = modelMapper.map(customerDto, Customer.class);
		
		// Check if a customer with the same name already exists
		Customer existingCustomer = customerRepo.findByCustomerName(customer.getCustomerName());

		if (existingCustomer != null) {
			throw new CustomerAlreadyExistsException(
					"Customer already exists with name: " + customer.getCustomerName());
		}
		
		Customer savedCustomer = customerRepo.save(customer);
		CustomerDTO savedCustomerDto = modelMapper.map(savedCustomer, CustomerDTO.class);
		return savedCustomerDto;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO, int customerId) {
		Customer existingCustomer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFound("Customer", "CustomerId", customerId));
		
		// Check if another customer with the same name exists
		Customer customerByName = customerRepo.findByCustomerName(customerDTO.getCustomerName());
		if (customerByName != null && !customerByName.getCustomerId().equals(existingCustomer.getCustomerId())) {
			throw new CustomerAlreadyExistsException(
					"Customer already exists with name: " + customerDTO.getCustomerName());
		}
		
		// Update all fields
		existingCustomer.setCustomerName(customerDTO.getCustomerName());
		existingCustomer.setEmail(customerDTO.getEmail());
		existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
		existingCustomer.setFaxNumber(customerDTO.getFaxNumber());
		existingCustomer.setAddressLine1(customerDTO.getAddressLine1());
		existingCustomer.setAddressLine2(customerDTO.getAddressLine2());
		existingCustomer.setCity(customerDTO.getCity());
		existingCustomer.setState(customerDTO.getState());
		existingCustomer.setZipcode(customerDTO.getZipcode());
		existingCustomer.setCountry(customerDTO.getCountry());
		existingCustomer.setActive(customerDTO.getActive());
		existingCustomer.setModifiedBy(customerDTO.getModifiedBy());
		
		Customer updatedCustomer = customerRepo.save(existingCustomer);

		// Convert back to DTO
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CustomerDTO updatedCustomerDto = modelMapper.map(updatedCustomer, CustomerDTO.class);
		return updatedCustomerDto;
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFound("Customer", "CustomerId", customerId));
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> getActiveCustomers() {
		return customerRepo.findByActiveTrue();
	}

	@Override
	public List<Customer> searchCustomersByName(String customerName) {
		return customerRepo.findByCustomerNameContainingIgnoreCase(customerName);
	}

	@Override
	public long getTotalCustomersCount() {
		return customerRepo.count();
	}

	@Override
	public long getActiveCustomersCount() {
		return customerRepo.countByActiveTrue();
	}

	@Override
	public boolean deactivateCustomer(Integer customerId) {
		try {
			Customer customer = getCustomerById(customerId);
			customer.setActive(false);
			customerRepo.save(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean activateCustomer(Integer customerId) {
		try {
			Customer customer = getCustomerById(customerId);
			customer.setActive(true);
			customerRepo.save(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Customer> advancedSearchCustomers(String searchTerm) {
		return customerRepo.searchCustomers(searchTerm);
	}

	@Override
	public List<Customer> getCustomersByCity(String city) {
		return customerRepo.findByCity(city);
	}

	@Override
	public List<Customer> getCustomersByState(String state) {
		return customerRepo.findByState(state);
	}

	@Override
	public List<Customer> getCustomersByCountry(String country) {
		return customerRepo.findByCountry(country);
	}

	@Override
	public List<Customer> getCustomersByCreatedBy(Integer createdBy) {
		return customerRepo.findByCreatedBy(createdBy);
	}
}
