package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.models.Admin;
import com.masai.models.Customer;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer>{
	
	
	public Admin findByEmail(String email);

//	@Query("select a.Customer from Address a where a.city=?1")
//	List<Customer> viewAllCustomer(String location);

}
