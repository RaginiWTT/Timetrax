package com.wtt.TimetraxRestApis.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.Customer;
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	// Custom query methods can be added here if needed
	// For example, to find a customer by name:
	// Optional<Customer> findByCustomerName(String customerName);

}
