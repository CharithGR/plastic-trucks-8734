package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.PackageException;
import com.masai.exceptions.RouteException;
import com.masai.exceptions.UserException;
import com.masai.models.CurrentUserSession;
import com.masai.models.Package;
import com.masai.models.Route;
import com.masai.repository.PackageDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	RouteDAO routeDao;//Jpa repositry
	@Autowired
	private SessionDAO sdao;
	@Autowired
	private PackageDAO packageDAO;
	
//====================================================ADD ROUTE SERVICE=====================================================
	@Override
	public Route AddRoute(Route route ,Integer packageId,String UUID) {
		CurrentUserSession existingUser = sdao.findByUuid(UUID);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		}
		else {
			Package package1=packageDAO.findById(packageId).orElseThrow(()->new PackageException("Invalid Package Id"));
									
			Route route2 = routeDao.save(route);
			package1.getListOfRouteinPackage().add(route2);
			route2.setRoutePackage(package1);
		return route2;
		}
	}
//====================================================UPDATE ROUTE SERVICE===================================================

	@Override
	public Route UpdateRoute(Route route ,String UUID) throws RouteException {
		CurrentUserSession existingUser = sdao.findByUuid(UUID);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		}
		else {
		Optional<Route> route2=routeDao.findById(route.getRouteId());
		if(route2.isPresent()){
			return routeDao.save(route);
		}
		throw new RouteException();
		}
	}
	
//====================================================DELETE ROUTE SERVICE=====================================================

	@Override
	public Route RemoveRoute(Integer RouteId ,String UUID) throws RouteException {
		CurrentUserSession existingUser = sdao.findByUuid(UUID);
		if(existingUser == null) {
			throw new UserException("User not Logged In");
		}
		else if(existingUser.getUserType().equalsIgnoreCase("customer")) {
			throw new LoginException("Access denied");
		}
		else {
		Optional<Route> rOpt=routeDao.findById(RouteId);
		if (!rOpt.isPresent()) {
			throw new RouteException("This Route is not present in database to delete.");
		}
		routeDao.delete(rOpt.get());
		return rOpt.get();
		}
	}
//====================================================VIEW ROUTE BY ID SERVICE==================================================
	@Override
	public Route SearchRoute(Integer RouteId) throws RouteException{
		Optional<Route> rOpt=routeDao.findById(RouteId);
		if (!rOpt.isPresent()) {
			throw new RouteException("The Route you are searching is not present in database.");
		}
		return rOpt.get();
	}

//====================================================VIEW ALL ROUTE SERVICE=====================================================

	@Override
	public List<Route> ViewRouteList() throws RouteException {
		List<Route> routeL=routeDao.findAll();
		if (routeL.isEmpty()) {
			throw new RouteException("No route is there in database to show.");
		}
		return routeL;
	}

}
