
package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.HotelException;
import com.masai.models.Hotel;

@Service
public interface HotelService {
	
	public Hotel AddHotel(Hotel hotel) throws HotelException;
	public Hotel UpdateHotel(Hotel hotel) throws HotelException;
 	public Hotel ViewHotelByName(String hotelName)throws HotelException;
 	public Hotel ViewHotelByAddress(String hotelAddress)throws HotelException;
	public List<Hotel> ViewAllHotels()throws HotelException;
	
}