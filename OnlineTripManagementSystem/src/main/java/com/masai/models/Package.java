package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Package {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer packageId;
	
	
	
//	@NotNull(message = "Enter the package name")
	private String packageName;
	
	
//	@NotNull(message = "Add package desciption")
	private String packageDescription;
	
	
	
//	@NotNull(message = "Add package type")
	private String packageType;
	
	
	
//	@NotNull(message = "Enter the package cost")
	private double packageCost;
	
	
	
	
	
	//	@NotNull(message = "Enter the payment details")
	private String paymentDetails;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Hotel packageHotel;
	
	
	@OneToMany(cascade =CascadeType.ALL,mappedBy = "ticketPackage")
	List<TicketDetails> listOfTicketDeatils=new ArrayList<>();
	
}
