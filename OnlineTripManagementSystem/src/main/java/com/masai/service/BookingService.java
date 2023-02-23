package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.exceptions.UserException;
import com.masai.models.Booking;

@Service
public interface BookingService {
	
	
	public Booking makeBooking(Booking booking, String uuid)throws BookingException, UserException;
	public Booking cancelBooking(Integer bookingId, String uuid)throws BookingException,UserException;
	public Booking viewBooking(Integer bookingId, String uuid)throws BookingException, UserException;
	public List<Booking> viewAllBooking(String uuid)throws BookingException, UserException ;

}
