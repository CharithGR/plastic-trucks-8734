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
	private String packageName;
	private String packageDescription;
	private String packageType;
	private double packageCost;
	private String paymentDetails;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Hotel packageHotel;
	
	
	@OneToMany(cascade =CascadeType.ALL,mappedBy = "ticketPackage")
	List<TicketDetails> listOfTicketDeatils=new ArrayList<>();
	
}
