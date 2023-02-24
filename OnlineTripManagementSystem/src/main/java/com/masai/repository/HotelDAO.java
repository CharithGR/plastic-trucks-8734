package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Hotel;

public interface HotelDAO extends JpaRepository<Hotel, Integer> {

	Optional<Hotel> findByHotelName(String hotelName);

	Optional<Hotel> findByAddress(String hotelAddress);

}
