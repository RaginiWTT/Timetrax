package com.wtt.TimetraxRestApis.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.CustomerDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
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
		Customer savedCustomer = customerRepo.save(customer);

		CustomerDTO savedCustomerDto = modelMapper.map(savedCustomer, CustomerDTO.class);
		return savedCustomerDto; // Return the saved CustomerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer, int id) {
		Customer existingCustomer = customerRepo.findById(id).get();
		if (existingCustomer != null) {
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
		Customer customer = customerRepo.findById(customerId).get();
		if (customer != null) {
			return customer;
		}
		return null;
	}

}
