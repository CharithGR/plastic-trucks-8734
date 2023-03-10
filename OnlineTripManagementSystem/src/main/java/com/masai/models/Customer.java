
package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	
//	@NotNull(message = "Please Enter Customer name")
	private String customerName;
	
//	@NotNull(message = "Enter password")
//    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
//    message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String customerPassword;
	
//	@NotNull(message = "Enter email id")
//	@Column(unique = true)
//	@Email(message = "Enter a valid email")
	private String email;
	
//	@NotNull(message = "Enter mobile number")
//	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Mobile Number Should be 10 digit, should be start with(6,7,8,9)")
	private String mobile;
	
//	@NotNull(message = "Please enter address")
	private String address;
	
	@ManyToOne
	@JsonIgnore
	private User customerUser;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "feedbackOfCustomer")
	private List<Feedback> listOfFeedback = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bookingUser")
	private List<Booking> listOfBookingOfCustomer=new ArrayList<>();
	
	
	
	
}
