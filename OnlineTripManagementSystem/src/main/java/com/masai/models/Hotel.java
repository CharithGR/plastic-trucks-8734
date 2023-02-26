package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
//	@NotNull(message = "field cannot be empty")
	private String hotelName;
	
//	@NotNull(message = "field cannot be empty")
	private String hotelType;
	
//	@NotNull(message = "field cannot be empty")
	private String hotelDescription;
	
//	@NotNull(message = "field cannot be empty")
	private String address;
	
//	@NotNull(message = "field cannot be empty")
	private Double rent;
	
//	@NotNull(message = "field cannot be empty")
	private String status;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "packageHotel")
	private List<Package> listOfPackageOfHotel=new ArrayList<>();
	
}
