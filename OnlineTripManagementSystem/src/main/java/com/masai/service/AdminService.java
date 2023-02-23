package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;

@Service
public interface AdminService {

			Customer addCustomer(Customer cust) throws CustomerException;
			
			Customer updateCustomer(Customer cust) throws CustomerException;
			
			Customer removeCustomer(Integer custId) throws CustomerException;
			
			Customer viewCustomer(Integer custId) throws CustomerException;
			
			List<Customer> viewAllCustomer(String location) throws CustomerException;
				
		}
	
	

