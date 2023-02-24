package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.models.Admin;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.LoginDTO;
import com.masai.repository.AdminDAO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.SessionDAO;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SessionDAO sdao;
	@Autowired
	private CustomerDAO cdao;
	@Autowired
	private AdminDAO admindao;
	
	
	@Override
	public String logIntoAccount(LoginDTO loginDTO) {
		if(loginDTO.getUserType().equalsIgnoreCase("Customer")) {
			Customer existingCustomer= cdao.findByEmail(loginDTO.getEmail());
			
			if(existingCustomer==null)throw new LoginException("Account does not exsits with this email");
			
			Optional<CurrentUserSession> validCustomerSessionOpt= sdao.findById(existingCustomer.getCustomerId());
			
			if(validCustomerSessionOpt.isPresent())	
				throw new LoginException("User already Logged In with this account");
				
			if(existingCustomer.getCustomerPassword().equals(loginDTO.getPassword())) {
				String key=RandomString.make(6);				
				CurrentUserSession currentUserSession=new CurrentUserSession(existingCustomer.getCustomerId(),"customer",key,LocalDateTime.now());
				sdao.save(currentUserSession);
				return "Login Successfull, key : "+key;	
			}else {
				throw new LoginException("Incorrect email/password");
			}
			
			
			
		}else if(loginDTO.getUserType().equalsIgnoreCase("Admin")) {
			
			Admin existingAdmin=admindao.findByEmail(loginDTO.getEmail());
			if(existingAdmin==null)throw new LoginException("Account does not exsits with this email");
			
			Optional<CurrentUserSession> validAdimSessionOpt= sdao.findById(existingAdmin.getAdminId());
			if(validAdimSessionOpt.isPresent())throw new LoginException("Admin already Logged In");
			if(existingAdmin.getPassword().equals(loginDTO.getPassword()) && existingAdmin.getEmail().equals(loginDTO.getEmail())) {
				String key=RandomString.make(6);				
				CurrentUserSession currentUserSession=new CurrentUserSession(existingAdmin.getAdminId(),"admin",key,LocalDateTime.now());
				sdao.save(currentUserSession);
				return "Login Successfull, key : "+key;			
				
				
			}else {
				throw new LoginException("Incorrect email/password");
			}
						
			
		}else {
			throw new LoginException("Please select Customer/Admin as userType");
		}
		
		
		
		
	}

	@Override
	public String logOutFromAccount(String key) {
		CurrentUserSession validCustomerSession = sdao.findByUuid(key);
		if(validCustomerSession == null) {
			throw new LoginException("Invalid Key");
			
		}
			sdao.delete(validCustomerSession);
		
		
		return "Log Out Successful";
	}

}
