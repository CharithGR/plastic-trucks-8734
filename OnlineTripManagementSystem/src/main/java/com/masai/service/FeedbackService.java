package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.models.Customer;
import com.masai.models.Feedback;

@Service
public interface FeedbackService {
	
	public Feedback AddFeedback(Feedback feedback, String uuid)throws FeedbackException;
	public Feedback FindByFeedbackId(Integer feedbackId, String uuid)throws FeedbackException;
//	public Customer FindByCustomerId(Integer customerId, String uuid)throws CustomerException;
	public List<Feedback> ViewAllFeedback(String uuid)throws FeedbackException;
	
}
