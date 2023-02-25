package com.masai.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentUserSession {
	
	@Id
//	@Column(unique = true)
	private Integer userId;
	
	@NotNull(message = "Enter user type")
	private String userType;

//	@NotNull(message = "Enter user id")
	private String uuid;
	
//	@NotNull(message = "Enter date & time")
//	@Future
//	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private LocalDateTime localDateTime;

}
