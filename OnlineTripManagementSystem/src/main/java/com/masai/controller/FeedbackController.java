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
import com.masai.models.Feedback;
import com.masai.service.FeedbackService;

@RestController
@RequestMapping("/feedbackservice")
public class FeedbackController {

	@Autowired
	private FeedbackService fService;
	
	@PostMapping("/feedback")
	public ResponseEntity<Feedback> addFeedbackHandler(@RequestBody Feedback feedback){
		Feedback newFeedback = fService.AddFeedback(feedback);
		return new ResponseEntity<Feedback>(newFeedback, HttpStatus.CREATED);
	}
	
	@GetMapping("/feedbackId/{feedbackId}")
	public ResponseEntity<Feedback> FindFeedbackByIdHandler(@PathVariable("feedbackId") Integer feedbackId){
		Feedback feedback = fService.FindByFeedbackId(feedbackId);
		return new ResponseEntity<Feedback>(feedback,HttpStatus.OK);
	}
	
	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> ViewAllFeedbacksHandler(){
		List<Feedback> fdbks = fService.ViewAllFeedback();
		return new ResponseEntity<List<Feedback>>(fdbks,HttpStatus.OK);
	}
	
}
