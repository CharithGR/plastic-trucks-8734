package com.masai.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private String ticketStatus;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "routeTicket")
	private Route ticketRoute;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Package ticketPackage;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Booking bookedTickets;
	
}
