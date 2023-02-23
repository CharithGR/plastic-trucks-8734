package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Booking;
import com.masai.service.BookingService;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookings")
	public ResponseEntity<Booking> addBookingHandler(@RequestBody Booking booking){
		Booking newbooking = bookingService.makeBooking(booking);
		return new ResponseEntity<Booking>(newbooking, HttpStatus.CREATED);
		
		
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public ResponseEntity<Booking> removeBookingHandler(@PathVariable("bookingId") Integer bookingId) {
		Booking deleBooking = bookingService.cancelBooking(bookingId);
		return new ResponseEntity<Booking>(deleBooking, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{bookingId}")
	public ResponseEntity<Booking> getBookingHandler(@PathVariable("bookingId") Integer bookingId){
		
		Booking booking = bookingService.viewBooking(bookingId);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	
	@GetMapping("bookings")
	public ResponseEntity<List<Booking>> getAllBookingHandler() {
		List<Booking> bookings = bookingService.viewAllBooking();
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
	}

}
