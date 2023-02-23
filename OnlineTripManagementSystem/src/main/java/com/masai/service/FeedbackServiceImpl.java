package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.ReportException;
import com.masai.models.Customer;
import com.masai.models.Feedback;
import com.masai.models.Report;
import com.masai.repository.CustomerDAO;
import com.masai.repository.FeedbackDAO;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackDAO fdao;
	
	@Autowired
	private CustomerDAO cdao;

	@Override
	public Feedback AddFeedback(Feedback feedback) throws FeedbackException {
		Feedback newfdbk = fdao.save(feedback);
		return newfdbk;
	}

	@Override
	public Feedback FindByFeedbackId(Integer feedbackId) throws FeedbackException {
		Optional<Feedback> opt = fdao.findById(feedbackId);
		if (opt.isPresent()) {
			Feedback foundfdbk = opt.get();
			return foundfdbk;
		} else {
			throw new FeedbackException("Feedback does not exist with Id :" + feedbackId);
		}
	}

//	@Override
//	public Feedback FindByCustomerId(Integer customerId) throws CustomerException {
//		Optional<Feedback> opt = fdao.findById(customerId);
//		if (opt.isPresent()) {
//			Feedback cust = opt.get();
//			return cust;
//		} else {
//			throw new CustomerException("Customer does not exist with Id :" + customerId);
//		}
//	}

	@Override
	public List<Feedback> ViewAllFeedback() throws FeedbackException {
		List<Feedback> feedback = fdao.findAll();
		if (feedback.size() == 0)
			throw new FeedbackException("No Feedback found..");
		else
			return feedback;
	}
}


