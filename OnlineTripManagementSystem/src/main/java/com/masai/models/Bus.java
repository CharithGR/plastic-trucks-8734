package com.masai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	private Integer busId;
	
//	@NotNull(message = "field cannot be empty")
	private String busType;
	
//	@NotNull(message = "field cannot be empty")
//	@Size(min = 4, max = 4, message="Length  of Bus Number should be of 4 digits")
	private String busNumber;
	
//	@NotNull(message = "field cannot be empty")
	private Integer capacity;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
	private Travels travelBus;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Route routeOfBus;
	
}