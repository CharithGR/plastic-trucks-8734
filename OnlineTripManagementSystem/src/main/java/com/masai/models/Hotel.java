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
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelId;
	
//	@NotNull(message = "Enter the hotel name")
	private String hotelName;
	
//	@NotNull(message = "Enter hotel type")
	private String hotelType;
	
//	@NotNull(message = "Add description of hotel")
	private String hotelDescription;
	
//	@NotNull(message = "Enter address of hotel")
	private String address;
	
//	@NotNull(message = "Enter hotel charges")
	private Double rent;
	
//	@NotNull(message = Please enter status")
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "packageHotel")
	private List<Package> listOfPackageOfHotel=new ArrayList<>();
	
}