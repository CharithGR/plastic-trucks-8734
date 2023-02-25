package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BusException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.RouteException;
import com.masai.exceptions.TravelsException;
import com.masai.models.Bus;
import com.masai.models.CurrentUserSession;
import com.masai.models.Route;
import com.masai.models.Travels;
import com.masai.repository.BusDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.TravelsDAO;


@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDAO busDAO;
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private TravelsDAO travelsDAO;
	@Autowired
	private RouteDAO routeDAO;
	
	

	@Override
	public Bus addBus(Bus bus, Integer routeId, Integer travesId, String key) {
		CurrentUserSession currentUserSession=sessionDAO.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to add bus/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		Route route=routeDAO.findById(routeId).orElseThrow(()->new RouteException("Invalid RouteId"));
		Travels travels=travelsDAO.findById(travesId).orElseThrow(()->new TravelsException("Invalid Travel Id"));
					
		route.setBusRoute(bus);
		travels.getListOfBusOfTravels().add(bus);
		bus.setRouteOfBus(route);
		bus.setTravelBus(travels);
		
				
		return busDAO.save(bus);		
		
	}



	@Override
	public String removeBus(Integer busId, String key) {
		CurrentUserSession currentUserSession=sessionDAO.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to remove bus/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		
		Bus bus=busDAO.findById(busId).orElseThrow(()->new BusException("Invalid Bus Id"));
		
		Route route=bus.getRouteOfBus();
		Travels travels=bus.getTravelBus();
		
		Bus routeBus=route.getBusRoute();
		List<Bus> travelbus=travels.getListOfBusOfTravels();
		
		travelbus.remove(bus);
		
		busDAO.delete(routeBus);
		busDAO.delete(bus);
		
		return "Bus Deleted";
	}

}
