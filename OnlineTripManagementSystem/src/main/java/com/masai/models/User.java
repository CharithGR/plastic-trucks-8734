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
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userType;
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "adminUser")
	@JsonIgnore
	private Admin admin;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customerUser")
	List<Customer> listOfCustomers=new ArrayList<>();
}
