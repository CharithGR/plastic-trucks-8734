package com.masai.models;

import java.time.LocalDateTime;
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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
//	@NotNull(message = "field cannot be empty")
	private String routeFrom;
	
//	@NotNull(message = "field cannot be empty")
	private String routeTo;
	
//	@NotNull(message = "field cannot be empty")
	private String pickupPoint;
	
//	@NotNull(message = "field cannot be empty")
//	private double fare;		
	
//	@NotNull(message = "field cannot be empty")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime departureTime;
	
//	@NotNull(message = "field cannot be empty")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime arrivalTime; 
	
//	@NotNull(message = "field cannot be empty")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
//	private LocalDateTime doj; 
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "routeOfBus")
	private Bus BusRoute;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Package routePackage;
	
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "ticketRoute")
	@JsonIgnore
	private List<TicketDetails> routeTickets=new ArrayList<>();
}
