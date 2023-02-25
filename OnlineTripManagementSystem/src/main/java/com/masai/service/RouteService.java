package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.RouteException;
import com.masai.models.Route;



@Service
public interface RouteService {
	public Route AddRoute(Route route,Integer packageId,String UUID);
	public Route UpdateRoute(Route route ,String UUID) throws RouteException;
	public Route RemoveRoute(Integer RouteId,String UUID) throws RouteException;
	public Route SearchRoute(Integer RouteId) throws RouteException;
	public List<Route> ViewRouteList() throws RouteException;
}
