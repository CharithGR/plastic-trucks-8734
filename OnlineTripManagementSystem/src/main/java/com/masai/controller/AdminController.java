package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;
import com.masai.service.AdminService;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@PostConstruct
	public void init() {
		adminService.addAdmin();
	}
	
//	@PostMapping("/customers")
//	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
//		System.out.println(cust);
//		
//		Customer saveCustomer = cService.addCustomer(cust);
//		
//		return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);
//	}
	
//	
//	@PutMapping("/customer")
//	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
//		
//		Customer updateCustomer = cService.updateCustomer(cust);
//		
//		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.CREATED);
//	}
//	
	
	@DeleteMapping("/customers")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam("Customerid") Integer custId,@RequestParam("Key")String key) throws CustomerException{
		
		String res = adminService.removeCustomer(custId, key);
		
		return new ResponseEntity<String>(res, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/customers/{custId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable("custId")Integer custId,@RequestParam("Key")String key) throws CustomerException{
		
		Customer viewCustomer = adminService.viewCustomer(custId, key);
		
		return new ResponseEntity<Customer>(viewCustomer, HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@RequestParam("Key") String key)throws CustomerException{
		
		List<Customer> viewAllCustomer = adminService.viewAllCustomer(key);		
		return new ResponseEntity<List<Customer>>(viewAllCustomer, HttpStatus.OK);
	}	
	
}