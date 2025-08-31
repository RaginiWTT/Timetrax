//package com.wtt.TimetraxRestApis.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.wtt.TimetraxRestApis.entity.Customer;
//public interface CustomerRepo extends JpaRepository<Customer, Integer> {
//
//
//	Customer findByCustomerName(String customerName);
//	
//	
//}

package com.wtt.TimetraxRestApis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wtt.TimetraxRestApis.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	// Find customer by exact name
	Customer findByCustomerName(String customerName);
	
	// Find active customers
	List<Customer> findByActiveTrue();
	
	// Find inactive customers
	List<Customer> findByActiveFalse();
	
	// Count active customers
	long countByActiveTrue();
	
	// Count inactive customers
	long countByActiveFalse();
	
	// Search customers by name (case-insensitive, partial match)
	List<Customer> findByCustomerNameContainingIgnoreCase(String customerName);
	
	// Find customers by email
	Customer findByEmail(String email);
	
	// Find customers by city
	List<Customer> findByCity(String city);
	
	// Find customers by state
	List<Customer> findByState(String state);
	
	// Find customers by country
	List<Customer> findByCountry(String country);
	
	// Custom query to find customers created by a specific resource
	@Query("SELECT c FROM Customer c WHERE c.createdBy = :createdBy")
	List<Customer> findByCreatedBy(@Param("createdBy") Integer createdBy);
	
	// Custom query to find customers modified by a specific resource
	@Query("SELECT c FROM Customer c WHERE c.modifiedBy = :modifiedBy")
	List<Customer> findByModifiedBy(@Param("modifiedBy") Integer modifiedBy);
	
	// Search customers by multiple fields
	@Query("SELECT c FROM Customer c WHERE " +
		   "LOWER(c.customerName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		   "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		   "LOWER(c.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		   "LOWER(c.state) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	List<Customer> searchCustomers(@Param("searchTerm") String searchTerm);
}
