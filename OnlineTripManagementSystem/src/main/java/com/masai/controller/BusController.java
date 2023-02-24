package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Bus;
import com.masai.service.BusService;

@RestController
public class BusController {

	@Autowired
	private BusService busService;
	
	@PostMapping("/buses")
	public ResponseEntity<Bus> addNewBus(@RequestBody Bus bus,@RequestParam("RouteId") Integer routeId, @RequestParam("TravelId")Integer travelId, @RequestParam("authKey")String key){
		Bus b=busService.addBus(bus, routeId, travelId, key);
		return new ResponseEntity<Bus>(b,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/buses")
	public ResponseEntity<String> deleteBus(@RequestParam("Bus Id")Integer busid,@RequestParam("authKey") String key){
		String s=busService.removeBus(busid, key);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
}
