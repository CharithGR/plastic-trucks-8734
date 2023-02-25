package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;

@Service
public interface AdminService {

		public void addAdmin();
	
//		public Customer addCustomer(Customer cust) throws CustomerException;
//			
//		public	Customer updateCustomer(Customer cust) throws CustomerException;
			
		public	String removeCustomer(Integer custId,String key);
			
		public	Customer viewCustomer(Integer custId,String key);
			
			List<Customer> viewAllCustomer(String key);
				
		}
	
	

