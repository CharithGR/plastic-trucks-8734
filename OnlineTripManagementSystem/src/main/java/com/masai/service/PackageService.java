package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.masai.models.Package;

import com.masai.exceptions.PackageException;

@Service
public interface PackageService {
	
	public Package addPackage(Package package1)throws PackageException;
	public Package deletePackage(Integer packageId)throws PackageException;
	public List<Package> viewAllPackage()throws PackageException;
	public Package searchPackage(Integer packageId)throws PackageException;

}
