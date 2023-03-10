package com.masai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TicketDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	
	
	
	@NotNull(message = "Please enter ticket status")
	private String ticketStatus;
	
	
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "routeTicket")
	private Route ticketRoute;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Package ticketPackage;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Booking bookedTickets;
	
}
