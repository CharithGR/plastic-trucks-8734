package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ReportException;
import com.masai.exceptions.UserException;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.Feedback;
import com.masai.models.Report;
import com.masai.repository.BookingDAO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.FeedbackDAO;
import com.masai.repository.SessionDAO;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackDAO fdao;
	
	@Autowired
	private CustomerDAO cdao;
	
	@Autowired
	private SessionDAO sdao;

	@Override
	public Feedback AddFeedback(Feedback feedback, String uuid) throws FeedbackException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("admin")) {
			throw new LoginException("Access denied");
		} else {
			
			Customer customer=cdao.findById(existingUser.getUserId()).orElseThrow();
			feedback.setFeedbackOfCustomer(customer);
			Feedback newfdbk=fdao.save(feedback);
			customer.getListOfFeedback().add(newfdbk);
			cdao.save(customer);
			return newfdbk;
		}
	}
	
	@Override
	public Feedback FindByFeedbackId(Integer feedbackId, String uuid) throws FeedbackException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		} else {
			Optional<Feedback> opt = fdao.findById(feedbackId);
			if (opt.isPresent()) {
				Feedback foundfdbk = opt.get();
				return foundfdbk;
			} else {
				throw new FeedbackException("Feedback does not exist with Id :" + feedbackId);
			}
		}
	}

//	@Override
//	public Customer FindByCustomerId(Integer customerId, String uuid) throws CustomerException {
//		CurrentUserSession existingUser = sdao.findByUuid(uuid);
//		
//		if(existingUser == null) {
//			throw new UserException("User not Logged In");
//		}
//		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
//			throw new LoginException("Access denied");
//		} else {
//			Optional<Customer> opt = cdao.findById(customerId);
//			if (opt.isPresent()) {
//				Customer cust = opt.get();
//				return cust;
//			} else {
//				throw new CustomerException("Customer does not exist with Id :" + customerId);
//			}
//		}
//	}
//
//	@Override
//	public List<Feedback> ViewAllFeedback(String uuid) throws FeedbackException {
//		CurrentUserSession existingUser = sdao.findByUuid(uuid);
//		if(existingUser == null) {
//			throw new UserException("User not Logged In");
//		}
//		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
//			List<Feedback> feedback = cdao.findByListOfFeedback();
//			if (feedback.size() == 0)
//				throw new FeedbackException("No Feedback found..");
//			else
//				return feedback;
//		} else {
//			List<Feedback> feedback = fdao.findAll();
//			if (feedback.size() == 0)
//				throw new FeedbackException("No Feedback found..");
//			else
//				return feedback;
//		}
//	}
}


