package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Travels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer travelsId;
	
//	@NotNull(message = "field cannot be empty")
	private String travelsName;
	
//	@NotNull(message = "field cannot be empty")
	private String agentName;
	
//	@NotNull(message = "field cannot be empty")
	private String address;
	
//	@NotNull(message = "field cannot be empty")
	private String contact;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "travelBus")
	List<Bus> listOfBusOfTravels=new ArrayList<>();
}
