package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Package;
import com.masai.service.PackageService;

@RestController
@RequestMapping("/packageService")
public class PackageController {
	
	@Autowired
	PackageService packageService;
	
	@PostMapping("/package/{uuid}")
	public ResponseEntity<Package> addPackageHandler(Package pac, @PathVariable("uuid") String uuid){
		
		Package newPac = packageService.addPackage(pac, uuid);
		
		return new ResponseEntity<Package>(newPac, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/package/{packageId}/{uuid}")
	public ResponseEntity<Package> deletePackageHandler(@PathVariable("packageId") Integer packageId,
														@PathVariable("uuid") String uuid){
	
	Package delPackage = packageService.deletePackage(packageId, uuid);
	
	return new ResponseEntity<Package>(delPackage, HttpStatus.OK);
	}
	
	@GetMapping("/package/{packageId}/{uuid}")
	public ResponseEntity<Package> getPackageHandler(@PathVariable("packageId") Integer packageId,
														@PathVariable("uuid") String uuid) {
		
		Package package1 = packageService.searchPackage(packageId, uuid);
		return new ResponseEntity<Package>(package1, HttpStatus.OK);
	}
	
	@GetMapping("/package/{uuid}")
	public ResponseEntity<List<Package>> getAllPackageHandler(@PathVariable("uuid") String uuid){
		List<Package> packages = packageService.viewAllPackage(uuid);
		return new ResponseEntity<List<Package>>(packages, HttpStatus.OK);
	}
	
	@PostMapping("/pacakages/hotel")
	public ResponseEntity<Package> addHotelToPacakage(@RequestParam("packageId")Integer packageId,@RequestParam("HotelId")Integer hotelId,
			@RequestParam("Key")String key){
		Package package1=packageService.addHotelToPackage(hotelId, packageId, key);
		return new ResponseEntity<Package>(package1,HttpStatus.OK);
	}
	@PostMapping("/packages/routes")
	public ResponseEntity<Package> addRouteToPacakage(@RequestParam("packageId")Integer packageId,@RequestParam("RouteId")Integer routeId,
			@RequestParam("Key")String key){
		Package package1=packageService.addRouteToPackage(routeId, packageId, key);
		return new ResponseEntity<Package>(package1,HttpStatus.OK);
	}
	
	
}
