
package com.masai.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
	
//	@NotNull(message = "field cannot be empty")
	private String bookingType;
	
//	@NotNull(message = "field cannot be empty")
	private String description;
	
//	@NotNull(message = "field cannot be empty")
	private String title;
	
//	@NotNull(message = "field cannot be empty")
//	@PastOrPresent
//	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate bookingDate;
	
//	@Min(1)
//	@Max(15)
	private Integer noOfTickets;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer bookingUser;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "bookedTickets")
	private TicketDetails bookedTicketsofCustomer;
	
	}
