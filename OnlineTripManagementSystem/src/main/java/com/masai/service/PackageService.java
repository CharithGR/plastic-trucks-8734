package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.masai.models.Package;

import com.masai.exceptions.PackageException;
import com.masai.exceptions.UserException;


@Service
public interface PackageService {
	
	public Package addPackage(Package package1, String uuid)throws PackageException, UserException;
	public Package deletePackage(Integer packageId, String uuid)throws PackageException, UserException;
	public List<Package> viewAllPackage(String uuid)throws PackageException, UserException;
	public Package searchPackage(Integer packageId, String uuid)throws PackageException, UserException;

}
