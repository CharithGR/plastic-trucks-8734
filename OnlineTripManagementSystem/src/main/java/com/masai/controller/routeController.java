package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.RouteException;
import com.masai.models.Route;
import com.masai.service.RouteService;


@RestController
public class routeController {
	@Autowired
	RouteService routeService;
	
	@PostMapping("/routes")
	public ResponseEntity<Route> addRoute(@RequestBody Route route ,@RequestParam("PacakageId")Integer packageId, @RequestParam("key")String key){
		Route route2 = routeService.AddRoute(route, packageId, key);
		return new ResponseEntity<Route>(route2,HttpStatus.OK);
	}
	
	@GetMapping("/routes")
	public ResponseEntity<List<Route>> getAllRoutes() throws RouteException{
		List<Route> routeList = routeService.ViewRouteList();
		return new ResponseEntity<List<Route>>(routeList,HttpStatus.OK);
	}
	
	@DeleteMapping("/routes/{routeId}/{key}")
	public ResponseEntity<Route> removeRoute(@PathVariable("routeId") int routeId, @PathVariable String key) throws RouteException{
		Route route = routeService.RemoveRoute(routeId,key);
		return new ResponseEntity<Route>(route,HttpStatus.OK);
	}
	
	@GetMapping("/routes/{routeId}")
	public ResponseEntity<Route> searchRoute(@PathVariable("routeId") int routeId) throws RouteException{
		Route route = routeService.SearchRoute(routeId);
		return new ResponseEntity<Route>(route,HttpStatus.FOUND);
	}
	
	@PutMapping("/routes/{key}")
	public ResponseEntity<Route> UpdateRoute(@RequestBody Route route, @PathVariable String key){
		Route route2=routeService.UpdateRoute(route,key);
		return new ResponseEntity<Route>(route2,HttpStatus.OK);
	}
	
}
