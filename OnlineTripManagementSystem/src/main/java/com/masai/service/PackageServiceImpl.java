package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.PackageException;
import com.masai.repository.PackageDAO;
import com.masai.models.Package;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	PackageDAO packageDAO;

	@Override
	public Package addPackage(Package package1) throws PackageException {
		return packageDAO.save(package1);
	}

	@Override
	public Package deletePackage(Integer packageId) throws PackageException {
		Optional<Package> pacOptional = packageDAO.findById(packageId);
		if(pacOptional.isPresent()) {
			Package package1 = pacOptional.get();
			
			packageDAO.delete(package1);
			
			return package1;
		}
		else
			throw new PackageException("No package with packageId :"+packageId);
	}

	@Override
	public List<Package> viewAllPackage() throws PackageException {
		List<Package> packages = packageDAO.findAll();
		
		if(packages.isEmpty()) {
			throw new PackageException("No package");
		}
		return packages;
	}

	@Override
	public Package searchPackage(Integer packageId) throws PackageException {
		Optional<Package> pacOptional = packageDAO.findById(packageId);
		
		if(pacOptional.isPresent()) {
			
			Package package1 =  pacOptional.get();
			return package1;
		}
		else {
			throw new PackageException("No package with packageId :"+packageId);
		}
		
	}

}
