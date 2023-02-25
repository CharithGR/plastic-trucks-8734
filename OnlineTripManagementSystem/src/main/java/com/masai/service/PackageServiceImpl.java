package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.HotelException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.PackageException;
import com.masai.exceptions.RouteException;
import com.masai.exceptions.UserException;
import com.masai.repository.HotelDAO;
import com.masai.repository.PackageDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;
import com.masai.models.CurrentUserSession;
import com.masai.models.Hotel;
import com.masai.models.Package;
import com.masai.models.Route;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	PackageDAO packageDAO;
	
	@Autowired
	SessionDAO sessionDAO;
	
	@Autowired
	HotelDAO hotelDAO;
	
	@Autowired
	RouteDAO routeDAO;
	

	@Override
	public Package addPackage(Package package1,String uuid) throws PackageException, UserException {
		
		CurrentUserSession currUser = sessionDAO.findByUuid(uuid);
		
		if(currUser == null) {
			throw new UserException("Please log in first");
		}
		
		if(currUser.getUserType().equalsIgnoreCase("customer")) {
			throw new UserException("Access Denied");
		}
		return packageDAO.save(package1);
	}

	@Override
	public Package deletePackage(Integer packageId, String uuid) throws PackageException, UserException {
		
		CurrentUserSession currUser = sessionDAO.findByUuid(uuid);
		
		if(currUser == null) {
			throw new UserException("Please log in first");
		}
		
		if(currUser.getUserType().equalsIgnoreCase("customer")) {
			throw new UserException("Access Denied");
		}
		
		
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
	public List<Package> viewAllPackage(String uuid) throws PackageException, UserException {
		
		CurrentUserSession currUser = sessionDAO.findByUuid(uuid);
		
		if(currUser == null) {
			throw new UserException("Please log in");
		}
		
		List<Package> packages = packageDAO.findAll();
		
		if(packages.isEmpty()) {
			throw new PackageException("No package");
		}
		return packages;
	}

	@Override
	public Package searchPackage(Integer packageId,String uuid) throws PackageException, UserException {
		
		CurrentUserSession currUser = sessionDAO.findByUuid(uuid);
		
		if(currUser == null) {
			throw new UserException("Please log in");
		}
		
		Optional<Package> pacOptional = packageDAO.findById(packageId);
		
		if(pacOptional.isPresent()) {
			
			Package package1 =  pacOptional.get();
			return package1;
		}
		else {
			throw new PackageException("No package with packageId :"+packageId);
		}
		
	}

	@Override
	public Package addHotelToPackage(Integer hotelId,Integer packageid, String key) {
		CurrentUserSession currentUserSession=sessionDAO.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to add hotel to package/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		
		Hotel hotel=hotelDAO.findById(hotelId).orElseThrow(()->new HotelException("Invalid Hotel Id"));
		Package package1=packageDAO.findById(packageid).orElseThrow(()->new PackageException("Invalid packageId"));
		
		hotel.getListOfPackageOfHotel().add(package1);
		package1.setPackageHotel(hotel);
		hotelDAO.save(hotel);
		
		
		return packageDAO.save(package1);
	}

	@Override
	public Package addRouteToPackage(Integer routeId, Integer packageid, String key) {
		CurrentUserSession currentUserSession=sessionDAO.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to add route to package/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		
		Route route=routeDAO.findById(routeId).orElseThrow(()->new RouteException("Invalid Route Id"));
		Package package1=packageDAO.findById(packageid).orElseThrow(()->new PackageException("Invalid packageId"));

		route.setRoutePackage(package1);
		package1.getListOfRouteinPackage().add(route);
		routeDAO.save(route);
		
		return packageDAO.save(package1);
	}

}
