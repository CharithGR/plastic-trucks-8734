package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Booking;
import com.masai.models.Customer;

public interface BookingDAO extends JpaRepository<Booking, Integer>{
	
	public List<Booking>  findByBookingUser(Customer bookingUser);

}
