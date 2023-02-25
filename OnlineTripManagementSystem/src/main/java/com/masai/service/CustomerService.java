package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;

import java.util.List;

@Service
public interface CustomerService {

		Customer addCustomer(Customer cust) throws CustomerException;
		
		Customer updateCustomer(Customer cust, String key) throws CustomerException;
		
		String removeCustomer(String key) throws CustomerException;
		
		Customer viewCustomer(String key) throws CustomerException;
		
//		List<Customer> viewAllCustomer(String location) throws CustomerException;
			
	}