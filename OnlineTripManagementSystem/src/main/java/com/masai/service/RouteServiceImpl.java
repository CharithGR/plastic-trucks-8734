package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.RouteException;
import com.masai.models.Route;
import com.masai.repository.RouteDAO;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	RouteDAO routeDao;//Jpa repositry
	
//====================================================ADD ROUTE SERVICE=====================================================
	@Override
	public Route AddRoute(Route route) {
		Route route2 = routeDao.save(route);
		return route;
	}
//====================================================UPDATE ROUTE SERVICE===================================================

	@Override
	public Route UpdateRoute(Route route) throws RouteException {
		Optional<Route> route2=routeDao.findById(route.getRouteId());
		if(route2.isPresent()){
			routeDao.save(route);
			return route;
		}
		throw new RouteException();
	}
	
//====================================================DELETE ROUTE SERVICE=====================================================

	@Override
	public Route RemoveRoute(Integer RouteId) throws RouteException {
		Optional<Route> rOpt=routeDao.findById(RouteId);
		if (!rOpt.isPresent()) {
			throw new RouteException("This Route is not present in database to delete.");
		}
		routeDao.delete(rOpt.get());
		return rOpt.get();
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
