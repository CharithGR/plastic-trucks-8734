package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
//	@NotNull(message = "field cannot be empty")
	private String adminName;
	
//	@NotNull(message = "field cannot be empty")
//    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
//    message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	

//	@NotNull(message = "field cannot be empty")
//	@Column(unique = true)
//	@Email(message = "Enter a valid email")
	private String email;
	
//	@NotNull(message = "field cannot be empty")
//	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Mobile Number Should be 10 digit, should be start with(6,7,8,9)")
	private String mobile;
	
	@OneToOne
	private User adminUser;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "reportAdmin")
	private List<Report> listOfReport=new ArrayList<>();
	
}