package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.models.Booking;

@Service
public interface BookingService {
	
	
	public Booking makeBooking(Booking booking)throws BookingException;
	public Booking cancelBooking(Integer bookingId)throws BookingException;
	public Booking viewBooking(Integer bookingId)throws BookingException;
	public List<Booking> viewAllBooking()throws BookingException ;

}
