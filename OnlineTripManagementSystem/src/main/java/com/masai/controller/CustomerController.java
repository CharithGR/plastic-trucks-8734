package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;
import com.masai.service.CustomerServiceImpl;

import jakarta.validation.Valid;


@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceImpl cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
		System.out.println(cust);
		
		Customer saveCustomer = cService.addCustomer(cust);
		
		return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/customer/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer cust, @PathVariable String key) throws CustomerException{
		
		Customer updateCustomer = cService.updateCustomer(cust, key);
		
		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/customers")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam("key") String key) throws CustomerException{
		
		String res = cService.removeCustomer(key);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
	@GetMapping("/customer")
	public ResponseEntity<Customer> viewCustomerHandler(@RequestParam("key") String key) throws CustomerException{
		
		Customer viewCustomer = cService.viewCustomer(key);
		
		return new ResponseEntity<Customer>(viewCustomer, HttpStatus.OK);
	}
	
	
//	@GetMapping("/customers/{location}")
//	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@PathVariable String location )throws CustomerException{
//		
//		List<Customer> viewAllCustomer = cService.viewAllCustomer(location);
//		
//		return new ResponseEntity<List<Customer>>(viewAllCustomer, HttpStatus.OK);
//	}	
	
}