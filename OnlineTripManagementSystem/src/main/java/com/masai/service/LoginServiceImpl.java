package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.LoginDTO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.SessionDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SessionDAO sdao;
	@Autowired
	private CustomerDAO cdao;
	
	
	
	@Override
	public String logIntoAccount(LoginDTO loginDTO) {
		if(loginDTO.getUserType().equalsIgnoreCase("User")) {
			Customer existingCustomer= cdao.findByEmail(loginDTO.getEmail());
			
			if(existingCustomer==null)throw new LoginException("Account does not exsits with this email");
			
			Optional<CurrentUserSession> validCustomerSessionOpt= sdao.findById(existingCustomer.getCustomerId());
			
			if(validCustomerSessionOpt.isPresent())	
				throw new LoginException("User already Logged In with this number");
				
			if(existingCustomer.getCustomerPassword().equals(loginDTO.getPassword())) {
				String key=RandomString.make(6);
				
				CurrentUserSession currentUserSession=new CurrentUserSession(existingCustomer.getCustomerId(), key, LocalDateTime.now());
				sdao.save(currentUserSession);
				return currentUserSession.toString();
			
			}
			
			
			
		}else if(loginDTO.getUserType().equalsIgnoreCase("Admin")) {
			
		}else {
			throw new LoginException("Please select User/Admin as userType");
		}
		
		
		
		
		return null;
	}

	@Override
	public String logOutFromAccount(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
