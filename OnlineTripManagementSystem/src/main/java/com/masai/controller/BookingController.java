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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Booking;
import com.masai.service.BookingService;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookings")
	public ResponseEntity<Booking> addBookingHandler(@RequestParam("PackageId")Integer packageId,
			@RequestParam("noOfTickets")Integer noOfTickets,
			@RequestParam("routeId")Integer routeId,
			@RequestParam("key")String key
			){
		Booking newbooking = bookingService.makeBooking(packageId, noOfTickets, routeId, key);
		return new ResponseEntity<Booking>(newbooking, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/bookings/{bookingId}/{uuid}")
	public ResponseEntity<String> removeBookingHandler(@PathVariable("bookingId") Integer bookingId,
														@PathVariable("uuid") String uuid) {
		String res=  bookingService.cancelBooking(bookingId, uuid);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{bookingId}/{uuid}")
	public ResponseEntity<Booking> getBookingHandler(@PathVariable("bookingId") Integer bookingId,
													@PathVariable("uuid") String uuid){
		
		Booking booking = bookingService.viewBooking(bookingId, uuid);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	
	@GetMapping("bookings/{uuid}")
	public ResponseEntity<List<Booking>> getAllBookingHandler(@PathVariable("uuid") String uuid) {
		List<Booking> bookings = bookingService.viewAllBooking(uuid);
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
	}

}
