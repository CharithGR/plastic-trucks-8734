package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Hotel;
import com.masai.service.HotelService;


@RestController
@RequestMapping("/hotelservice")
public class HotelController {
	
	@Autowired
	private HotelService hService;
	
	@PostMapping("/hotels")
	public ResponseEntity<Hotel> addHotelHandler(@RequestBody Hotel hotel,@RequestParam("Key")String key){
		Hotel newHotel = hService.AddHotel(hotel,key);
		return new ResponseEntity<Hotel>(newHotel, HttpStatus.CREATED);
	}
	
	@PutMapping("/hotels")
		public ResponseEntity<Hotel> updateHotelHandler(@RequestBody Hotel hotel,@RequestParam("Key")String key){
		Hotel updatedHotel = hService.UpdateHotel(hotel,key);
		return new ResponseEntity<Hotel>(updatedHotel, HttpStatus.CREATED);
	}
	
	@GetMapping("/hotels/{hotelName}")
	public ResponseEntity<Hotel> ViewHotelByNameHandler(@PathVariable("hotelName") String hotelName,@RequestParam("Key")String key){
		Hotel hotel = hService.ViewHotelByName(hotelName,key);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelAddress}")
	public ResponseEntity<Hotel> ViewHotelByAddressHandler(@PathVariable("hotelAddress") String hotelAddress,@RequestParam("Key")String key){
		Hotel hotel = hService.ViewHotelByAddress(hotelAddress,key);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}	

	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> ViewAllHotelHandler(@RequestParam("Key")String key){
		List<Hotel> hotel = hService.ViewAllHotels(key);
		return new ResponseEntity<List<Hotel>>(hotel, HttpStatus.OK);
	}
	
	@DeleteMapping("/hotels")
	public ResponseEntity<String> removeHotel(@RequestParam("hotelId")Integer hotelId, @RequestParam("Key")String key){
		String res=hService.deleteHotel(hotelId, key);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}

}