package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Customer;
import com.masai.models.Feedback;
import com.masai.service.CustomerService;
import com.masai.service.FeedbackService;

@RestController
@RequestMapping("/feedbackservice")
public class FeedbackController {

	@Autowired
	private FeedbackService fService;
	
	@PostMapping("/feedback/{uuid}")
	public ResponseEntity<Feedback> addFeedbackHandler(@RequestBody Feedback feedback, @PathVariable("uuid") String uuid){
		Feedback newFeedback = fService.AddFeedback(feedback, uuid);
		return new ResponseEntity<Feedback>(newFeedback, HttpStatus.CREATED);
	}
	
	@GetMapping("/feedbackId/{feedbackId}/{uuid}")
	public ResponseEntity<Feedback> FindFeedbackByIdHandler(@PathVariable("feedbackId") Integer feedbackId, @PathVariable("uuid") String uuid){
		Feedback feedback = fService.FindByFeedbackId(feedbackId, uuid);
		return new ResponseEntity<Feedback>(feedback,HttpStatus.OK);
	}
	
//	@GetMapping("/feedbackId/{customerId}/{uuid}")
//	public ResponseEntity<Customer> FindCustomerByIdHandler(@PathVariable("customerId") Integer customerId, @PathVariable("uuid") String uuid){
//		Customer feedback = fService.FindByCustomerId(customerId, uuid);
//		return new ResponseEntity<Customer>(feedback,HttpStatus.OK);
//	}
	
//	@GetMapping("/feedbacks/{uuid}")
//	public ResponseEntity<List<Feedback>> ViewAllFeedbacksHandler(@PathVariable("uuid") String uuid){
//		List<Feedback> fdbks = fService.ViewAllFeedback(uuid);
//		return new ResponseEntity<List<Feedback>>(fdbks,HttpStatus.OK);
//	}
	
}
