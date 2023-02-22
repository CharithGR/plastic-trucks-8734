package com.masai.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private String routeFrom;
	private String routeTo;
	private String pickupPoint;
	private double fare;	
	private LocalDateTime departureTime; 
	private LocalDateTime arrivalTime; 
	private LocalDateTime doj; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "routeOfBus")
	private List<Bus> listOfBusInRoute=new ArrayList<>();
	
	
	@OneToOne
	private TicketDetails routeTicket;
}
