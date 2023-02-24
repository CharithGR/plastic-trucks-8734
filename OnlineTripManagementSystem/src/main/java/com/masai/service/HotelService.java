
package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.HotelException;
import com.masai.models.Hotel;

@Service
public interface HotelService {
	
	public Hotel AddHotel(Hotel hotel, String uuid) throws HotelException;
	public Hotel UpdateHotel(Hotel hotel, String uuid) throws HotelException;
 	public Hotel ViewHotelByName(String hotelName, String uuid)throws HotelException;
 	public Hotel ViewHotelByAddress(String hotelAddress, String uuid)throws HotelException;
	public List<Hotel> ViewAllHotels(String uuid)throws HotelException;
	
}