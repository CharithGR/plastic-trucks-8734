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
	
//	@NotNull(message = "Route from field cannot be empty ")
	private String routeFrom;
	
//	@NotNull(message = "Route to field cannot be empty ")
	private String routeTo;
	
//	@NotNull(message = "Enter the pickup point")
	private String pickupPoint;
	
//	@NotNull(message = "Please enter fare")
	private double fare;		
	
//	@NotNull(message = "Enter time of departure")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime departureTime;
	
//	@NotNull(message = "Enter time of arrival")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime arrivalTime; 
	
//	@NotNull(message = "Enter date of journey")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime doj; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "routeOfBus")
	private List<Bus> listOfBusInRoute=new ArrayList<>();
	
	
	@OneToOne
	@JsonIgnore
	private TicketDetails routeTicket;
}
