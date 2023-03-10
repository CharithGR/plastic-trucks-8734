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
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.TravelsException;
import com.masai.models.Travels;
import com.masai.service.TravelsService;

@RestController
public class TravelsController {
	
	@Autowired
	TravelsService travelsService;
	
	@PostMapping("/travels/{key}")
	public ResponseEntity<Travels> addTravles(@RequestBody Travels travels,@PathVariable String key) throws TravelsException{
		Travels travels2 = travelsService.addTravels(travels,key); 
		return new ResponseEntity<Travels>(travels2,HttpStatus.OK);
	}
	
	@GetMapping("/travels")
	public ResponseEntity<List<Travels>> allTravles() throws TravelsException{
		List<Travels> travelsList = travelsService.viewTravels(); 
		return new ResponseEntity<List<Travels>>(travelsList,HttpStatus.OK);
	}
	
	@PutMapping("/travels/{key}")
	public ResponseEntity<Travels> updateTravels(@RequestBody Travels travels,@PathVariable String key) throws TravelsException{
		Travels travels2 = travelsService.updateTravels(travels,key);
		return new ResponseEntity<Travels>(travels2,HttpStatus.OK);
	}
	
	@DeleteMapping("/travels/{id}/{key}")
	public ResponseEntity<Travels> deleteTravels(@PathVariable("id") int id,@PathVariable String key){
		Travels travels=travelsService.removeTravels(id,key);
		return new ResponseEntity<Travels>(travels,HttpStatus.OK);
	}
	
	@GetMapping("/travels/{id}")
	public ResponseEntity<Travels> SearchTravels(@PathVariable("id") int id){
		Travels travels=travelsService.searchTravels(id);
		return new ResponseEntity<Travels>(travels,HttpStatus.OK);
	}
}
