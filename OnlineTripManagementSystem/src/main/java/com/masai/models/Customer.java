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
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String customerName;
	private String customerPassword;
	private String email;
	private String mobile;
	private String address;
	
	@ManyToOne
	@JsonIgnore
	private User customerUser;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "feedbackOfCustomer")
	private List<Feedback> listOfFeedback=new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bookingUser")
	private List<Booking> listOfBookingOfCustomer=new ArrayList<>();
	
	
	
	
}
