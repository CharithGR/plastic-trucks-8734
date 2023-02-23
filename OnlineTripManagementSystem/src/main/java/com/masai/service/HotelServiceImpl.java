
package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.HotelException;
import com.masai.models.Hotel;
import com.masai.repository.HotelDAO;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelDAO hdao;

	@Override
	public Hotel AddHotel(Hotel hotel) throws HotelException {
		Hotel newHotel = hdao.save(hotel);
		return newHotel;
	}

	@Override
	public Hotel UpdateHotel(Hotel hotel) throws HotelException {
		Optional<Hotel> opt = hdao.findById(hotel.getHotelId());
		if(opt.isPresent()) {
			Hotel updated = hdao.save(hotel);
			return updated;
		} else {
			throw new HotelException("Invalid hotel details");
		}
	}

	@Override
	public Hotel ViewHotelByName(String hotelName) throws HotelException {
		Optional<Hotel> opt = hdao.findByHotelName(hotelName);
		 if(opt.isPresent()) {
			 Hotel hotel = opt.get();
			 return hotel;
		 }else {
			 throw new HotelException(hotelName + " hotel does not exist");
		 }
	}

	@Override
	public Hotel ViewHotelByAddress(String hotelAddress) throws HotelException {
		Optional<Hotel> opt = hdao.findByHotelAddress(hotelAddress);
		 if(opt.isPresent()) {
			 Hotel hotel = opt.get();
			 return hotel;
		 }else {
			 throw new HotelException(hotelAddress + " hotel does not exist");
		 }
	}

	@Override
	public List<Hotel> ViewAllHotels() throws HotelException {
		List<Hotel> hotel = hdao.findAll();
		if (hotel.size() == 0) {
			throw new HotelException("No hotels found..");
		} else {
			return hotel;
		}
	}

}