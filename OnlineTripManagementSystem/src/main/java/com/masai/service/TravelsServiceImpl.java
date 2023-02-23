package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.TravelsException;
import com.masai.models.Travels;
import com.masai.repository.TravelsDAO;

@Service
public class TravelsServiceImpl implements TravelsService{
	@Autowired
	TravelsDAO travelsDao;//Jpa repositry
	
//=============================================Add Travels====================================================================
	
	@Override
	public Travels addTravels(Travels travels) throws TravelsException {
		Optional<Travels> tOpt=travelsDao.findById(travels.getTravelsId());//find travels if exist
		if (tOpt.isPresent()) {
			throw new TravelsException("This Travelservice is already available in database");
		}
		return travelsDao.save(travels);
	}
	
//=============================================Update Travels details ==========================================================
	
	@Override
	public Travels updateTravels(Travels travels) throws TravelsException {
		Optional<Travels> tOpt=travelsDao.findById(travels.getTravelsId());//find travels to update
		if (!tOpt.isPresent()) {
			throw new TravelsException("This Travelservice is not available in database to update");
		}
		return travelsDao.save(travels);
	}
	
//=============================================Remove Travels====================================================================
	
	@Override
	public Travels removeTravels(int travelId) throws TravelsException {
		Optional<Travels> tOpt=travelsDao.findById(travelId);//find travels if exist with this Id
		if (!tOpt.isPresent()) {
			throw new TravelsException("This Travelservice is not present in database to remove");
		}
		travelsDao.delete(tOpt.get());
		return tOpt.get();
	}
	
//=============================================Search Travels by Id==============================================================
	@Override
	public Travels searchTravels(int travelId) throws TravelsException {
		Optional<Travels> tOpt=travelsDao.findById(travelId);//searching travels if exist with this Id
		if (!tOpt.isPresent()) {
			throw new TravelsException("This Travelservice is not present in database to show");
		}
		return tOpt.get();
	}
	
//=============================================Get All Travels====================================================================
	
	@Override
	public List<Travels> viewTravels() throws TravelsException {
		List<Travels> travelsL=travelsDao.findAll();
		if (travelsL.isEmpty()) {
			throw new TravelsException("This Travelservice is not present in database to remove");
		}
		return travelsL;
	}

}
