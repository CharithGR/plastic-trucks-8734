
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
	
//	@NotNull(message = "Enter the booking Type")
	private String bookingType;
	
//	@NotNull(message = "Enter description")
	private String description;
	
//	@NotNull(message = "Please enter title")
	private String title;
	
//	@NotNull(message = "Enter the booking date")
//	@PastOrPresent
//	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate bookingDate;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer bookingUser;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bookedTickets")
	private List<TicketDetails> listOfBookedTickets=new ArrayList<>();
}
