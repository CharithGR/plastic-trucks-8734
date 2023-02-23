package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.models.LoginDTO;
@Service
public interface LoginService {

	
	public String logIntoAccount(LoginDTO loginDTO);
	public String logOutFromAccount(String key);
}
