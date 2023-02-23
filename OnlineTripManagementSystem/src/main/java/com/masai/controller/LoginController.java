package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.LoginDTO;
import com.masai.service.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoginController {
	
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto){
		String res=loginService.logIntoAccount(dto);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
}
