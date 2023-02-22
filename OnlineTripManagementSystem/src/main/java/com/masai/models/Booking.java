package com.masai.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	private String bookingType;
	private String description;
	private String title;
	private LocalDate bookingDate;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer bookingUser;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bookedTickets")
	private List<TicketDetails> listOfBookedTickets=new ArrayList<>();
}
