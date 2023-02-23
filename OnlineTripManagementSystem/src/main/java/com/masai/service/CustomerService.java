package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;

import java.util.List;

@Service
public interface CustomerService {

		Customer addCustomer(Customer cust) throws CustomerException;
		
		Customer updateCustomer(Customer cust, String key) throws CustomerException;
		
		Customer removeCustomer(Integer custId, String key) throws CustomerException;
		
		Customer viewCustomer(Integer custId) throws CustomerException;
		
		List<Customer> viewAllCustomer(String location) throws CustomerException;
			
	}