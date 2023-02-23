package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.models.Booking;
import com.masai.repository.BookingDAO;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingDAO bookingDAO;

	@Override
	public Booking makeBooking(Booking booking) throws BookingException {
		return bookingDAO.save(booking);
	}

	@Override
	public Booking cancelBooking(Integer bookingId) throws BookingException {
		
		Optional<Booking> optional = bookingDAO.findById(bookingId);
		
		if(optional.isPresent()) {
			Booking booking = optional.get();
			bookingDAO.delete(booking);
			
			return booking;
		}
		else
			throw new BookingException("There is no booking with bookinId :"+bookingId);
	}

	@Override
	public Booking viewBooking(Integer bookingId) throws BookingException {
		
		Optional<Booking> optional = bookingDAO.findById(bookingId);
		
		if(optional.isPresent()) {
			Booking booking = optional.get();
			return booking;
		}
		else
			throw new BookingException("There is no booking with bookingId :"+bookingId);
	}

	@Override
	public List<Booking> viewAllBooking() throws BookingException {
		List<Booking> bookings = bookingDAO.findAll();
		if(bookings.isEmpty()) {
			throw new BookingException("No booking till now");
		}
		else
			return bookings;
	}

}
