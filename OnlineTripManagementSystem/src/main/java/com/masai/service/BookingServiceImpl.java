package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.exceptions.UserException;
import com.masai.models.Booking;
import com.masai.models.CurrentUserSession;
import com.masai.models.User;
import com.masai.repository.BookingDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.UserDAO;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	SessionDAO sessionDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	UserDAO userDAO;

	@Override
	public Booking makeBooking(Booking booking, String uuid) throws BookingException, UserException {
		CurrentUserSession existingUser = sessionDAO.findByUuid(uuid);
		
		if(existingUser == null) {
			throw new UserException("User not logged in");
		}
		
		
		return bookingDAO.save(booking);
	}

	@Override
	public Booking cancelBooking(Integer bookingId, String uuid) throws BookingException, UserException {
		
		CurrentUserSession existingUser = sessionDAO.findByUuid(uuid);
		
		if(existingUser == null) {
			throw new UserException("Please log in first");
		}
		
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
	public Booking viewBooking(Integer bookingId, String uuid) throws BookingException, UserException {
		
		CurrentUserSession existingUser =  sessionDAO.findByUuid(uuid);
		
		if(existingUser == null) {
			throw new UserException("Please log in");
		}
		
		Optional<Booking> optional = bookingDAO.findById(bookingId);
		
		if(optional.isPresent()) {
			Booking booking = optional.get();
			return booking;
		}
		else
			throw new BookingException("There is no booking with bookingId :"+bookingId);
	}

	@Override
	public List<Booking> viewAllBooking(String uuid) throws BookingException, UserException {
		
		CurrentUserSession exUserSession = sessionDAO.findByUuid(uuid);
		
		if(exUserSession == null) {
			throw new UserException("Please log in");
		}
		
		List<Booking> bookings = bookingDAO.findAll();
		if(bookings.isEmpty()) {
			throw new BookingException("No booking till now");
		}
		else
			return bookings;
	}

}
