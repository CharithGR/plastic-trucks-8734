package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.models.Bus;

@Service
public interface BusService {
	
	public Bus addBus(Bus bus,Integer routeId,Integer travesId,String key);
	
	public String removeBus(Integer busId,String key);
}
