
package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.HotelException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserException;
import com.masai.models.CurrentUserSession;
import com.masai.models.Hotel;
import com.masai.repository.HotelDAO;
import com.masai.repository.SessionDAO;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelDAO hdao;
	
	@Autowired
	private SessionDAO sdao;

	@Override
	public Hotel AddHotel(Hotel hotel, String uuid) throws HotelException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not logged in");
		}
		if(existingUser.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");

		Hotel newHotel = hdao.save(hotel);
		return newHotel;
	}

	@Override
	public Hotel UpdateHotel(Hotel hotel, String uuid) throws HotelException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not logged in");
		} else {
			
			if(existingUser.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
			
			Optional<Hotel> opt = hdao.findById(hotel.getHotelId());
			if(opt.isPresent()) {
				Hotel updated = hdao.save(hotel);
				return updated;
			} else {
				throw new HotelException("Invalid hotel details");
			}			
		}
	}

	@Override
	public Hotel ViewHotelByName(String hotelName, String uuid) throws HotelException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not logged in");
		} else {
			Optional<Hotel> opt = hdao.findByHotelName(hotelName);
			 if(opt.isPresent()) {
				 Hotel hotel = opt.get();
				 return hotel;
			 }else {
				 throw new HotelException(hotelName + " hotel does not exist");
			 }			
		}
	}

	@Override
	public Hotel ViewHotelByAddress(String hotelAddress, String uuid) throws HotelException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not logged in");
		} else {		
			Optional<Hotel> opt = hdao.findByAddress(hotelAddress);
			 if(opt.isPresent()) {
				 Hotel hotel = opt.get();
				 return hotel;
			 }else {
				 throw new HotelException(hotelAddress + " hotel does not exist");
			 }
		 }
	}

	@Override
	public List<Hotel> ViewAllHotels(String uuid) throws HotelException {
		CurrentUserSession existingUser = sdao.findByUuid(uuid);
		if(existingUser == null) {
			throw new UserException("User not logged in");
		} else {	
			List<Hotel> hotel = hdao.findAll();
			if (hotel.size() == 0) {
				throw new HotelException("No hotels found..");
			} else {
				return hotel;
			}
		}
	}

}