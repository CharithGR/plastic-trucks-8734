package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Hotel;
import com.masai.service.HotelService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/hotelservice")
public class HotelController {
	
	@Autowired
	private HotelService hService;
	
	@PostMapping("/hotels")
	public ResponseEntity<Hotel> addHotelHandler(@RequestBody Hotel hotel){
		Hotel newHotel = hService.AddHotel(hotel);
		return new ResponseEntity<Hotel>(newHotel, HttpStatus.CREATED);
	}
	
	@PutMapping("/hotels")
		public ResponseEntity<Hotel> updateHotelHandler(@RequestBody Hotel hotel){
		Hotel updatedHotel = hService.UpdateHotel(hotel);
		return new ResponseEntity<Hotel>(updatedHotel, HttpStatus.CREATED);
	}
	
	@GetMapping("/hotels/{hotelName}")
	public ResponseEntity<Hotel> ViewHotelByNameHandler(@PathVariable("hotelName") String hotelName){
		Hotel hotel = hService.ViewHotelByName(hotelName);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelAddress}")
	public ResponseEntity<Hotel> ViewHotelByAddressHandler(@PathVariable("hotelAddress") String hotelAddress){
		Hotel hotel = hService.ViewHotelByAddress(hotelAddress);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}	

	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> ViewAllHotelHandler(){
		List<Hotel> hotel = hService.ViewAllHotels();
		return new ResponseEntity<List<Hotel>>(hotel, HttpStatus.OK);
	}

}