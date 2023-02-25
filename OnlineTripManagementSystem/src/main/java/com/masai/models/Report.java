
package com.masai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reportId;
	
	//	@NotNull(message = "Enter report name")
	private String reportName;
	
	//	@NotNull(message = "Enter report type")
	private String reportType;
	
	@ManyToOne
	@JsonIgnore
	private Admin reportAdmin;
	
	
}
