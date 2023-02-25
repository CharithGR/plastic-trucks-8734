package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.TravelsException;
import com.masai.models.Travels;

@Service
public interface TravelsService {
	Travels addTravels(Travels travels , String uuid)  throws TravelsException;
	Travels updateTravels(Travels travels , String uuid) throws TravelsException;
	Travels removeTravels(int travelId , String uuid) throws TravelsException;
	Travels searchTravels(int travelId) throws TravelsException;
	List<Travels> viewTravels() throws TravelsException;
}
