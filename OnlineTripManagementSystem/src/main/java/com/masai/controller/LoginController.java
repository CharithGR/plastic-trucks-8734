package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.LoginDTO;
import com.masai.service.LoginService;


@RestController
public class LoginController {
	
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO dto){
		String res=loginService.logIntoAccount(dto);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam("authKey") String key){
		String res=loginService.logOutFromAccount(key);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
}
