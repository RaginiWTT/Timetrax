package com.wtt.TimetraxRestApis.service;

import java.util.List;
import java.util.Optional;

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
	// Implement the methods defined in CustomerService interface

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
//		Optional<Customer> existingCustomer = customerRepo.findByCustomerName(customer.getCustomerName());
		Customer existingCustomer = customerRepo.findByCustomerName(customer.getCustomerName());

		if (existingCustomer != null) {
			// If a customer with the same name already exists, return null or throw an
			// exception
			throw new CustomerAlreadyExistsException(
					"Customer already exists with name: " + customer.getCustomerName());
		}
		Customer savedCustomer = customerRepo.save(customer);

		CustomerDTO savedCustomerDto = modelMapper.map(savedCustomer, CustomerDTO.class);
		return savedCustomerDto; // Return the saved CustomerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer, int id) {
		Customer existingCustomer = customerRepo.findById(id).orElseThrow(() 
				-> new ResourceNotFound("Customer","CustomerId", id));
		
		if (existingCustomer != null) {
			
			Customer customerByName = customerRepo.findByCustomerName(customer.getCustomerName());
			if (customerByName != null && customerByName.getCustomerId()!= existingCustomer.getCustomerId()) {
				throw new CustomerAlreadyExistsException(
						"Customer already exists with name: " + customer.getCustomerName());
			}
			
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
			Customer updateCustomer = customerRepo.save(existingCustomer);

			// this matching strategy will force ModelMapper to map only exact matches,
			// avoiding type confusion
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			CustomerDTO updateCustomerDto = modelMapper.map(updateCustomer, CustomerDTO.class);
			return updateCustomerDto;
		}
		return null;
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFound("Customer", "CustomerId", customerId));
		
		
//		if (customer != null) {
//			return customer;
//		}
//		else {
//			throw new ResourceNotFound("Customer", "CustomerId", customerId);
//		}
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer> customers = customerRepo.findAll();
//		if (customers != null && !customers.isEmpty()) {
//			// Convert List<Customer> to List<CustomerDTO>
//			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//			List<CustomerDTO> customerDtos = customers.stream()
//					.map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
//			return customerDtos;
//		}
		return customers;
	}
	
	

}
