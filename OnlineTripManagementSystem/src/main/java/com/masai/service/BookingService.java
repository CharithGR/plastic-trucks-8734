package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.HotelException;
import com.masai.exceptions.PackageException;
import com.masai.models.Booking;

@Service
public interface BookingService {
	
	
	public Booking makeBooking(Booking booking)throws BookingException;
	public Booking cancelBooking(Integer bookingId,Integer customerId)throws BookingException;
	public Booking viewBooking(int bookingId)throws BookingException;
	public List<Booking> viewAllBooking()throws BookingException ;

}
