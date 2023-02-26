
package com.masai.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;
	
//	@NotNull(message = "field cannot be empty")
	private String feedback;
	
//	@NotNull(message = "field cannot be empty")
//    @Pattern(regexp = "^((?=.*[*])(?=.*[1-5])){2}$",
//    message = "Rating must contain atleast 1 (*) and 1 digit from 1 to 5, length should be 2")
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private Double rating;
	
//	@NotNull(message = "field cannot be empty")
//	@PastOrPresent
//	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate submitDate;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer feedbackOfCustomer;
	
}
