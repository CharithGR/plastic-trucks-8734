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
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Package;
import com.masai.service.PackageService;

@RestController
@RequestMapping("/packageService")
public class PackageController {
	
	@Autowired
	PackageService packageService;
	
	@PostMapping("/package")
	public ResponseEntity<Package> addPackageHandler(Package pac){
		
		Package newPac = packageService.addPackage(pac);
		
		return new ResponseEntity<Package>(newPac, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/package/{packageId}")
	public ResponseEntity<Package> deletePackageHandler(@PathVariable("packageId") Integer packageId){
	
	Package delPackage = packageService.deletePackage(packageId);
	
	return new ResponseEntity<Package>(delPackage, HttpStatus.OK);
	}
	
	@GetMapping("/package/{packageId}")
	public ResponseEntity<Package> getPackageHandler(@PathVariable("packageId") Integer packageId) {
		
		Package package1 = packageService.searchPackage(packageId);
		return new ResponseEntity<Package>(package1, HttpStatus.OK);
	}
	
	@GetMapping("/package")
	public ResponseEntity<List<Package>> getAllPackageHandler(){
		List<Package> packages = packageService.viewAllPackage();
		return new ResponseEntity<List<Package>>(packages, HttpStatus.OK);
	}
}
