package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.models.Customer;
import com.masai.models.User;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{
	
	
	
	public Customer findByEmail(String email);
	public Customer  findByCustomerUser(User customerUser);

//	@Query("select a.Customer from Address a where a.city=?1")
//	List<Customer> viewAllCustomer(String location);

}



