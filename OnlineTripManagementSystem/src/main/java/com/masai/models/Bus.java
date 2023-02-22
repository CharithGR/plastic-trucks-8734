package com.masai.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	private String busType;
	private String busNumber;
	private Integer capacity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bus travelBus;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Route routeOfBus;
	
}
