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
import com.masai.service.AdminServiceImpl;

import jakarta.validation.Valid;


@RestController
public class AdminController {

	@Autowired
	private AdminServiceImpl cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
		System.out.println(cust);
		
		Customer saveCustomer = cService.addCustomer(cust);
		
		return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
		
		Customer updateCustomer = cService.updateCustomer(cust);
		
		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/customers/{custId}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable Integer custId) throws CustomerException{
		
		Customer removeCustomer = cService.removeCustomer(custId);
		
		return new ResponseEntity<Customer>(removeCustomer, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/customer/{custId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable Integer custId) throws CustomerException{
		
		Customer viewCustomer = cService.viewCustomer(custId);
		
		return new ResponseEntity<Customer>(viewCustomer, HttpStatus.OK);
	}
	
	
	@GetMapping("/customers/{location}")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@PathVariable String location )throws CustomerException{
		
		List<Customer> viewAllCustomer = cService.viewAllCustomer(location);
		
		return new ResponseEntity<List<Customer>>(viewAllCustomer, HttpStatus.OK);
	}	
	
}