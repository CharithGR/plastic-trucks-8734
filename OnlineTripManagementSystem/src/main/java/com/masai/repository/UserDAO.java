package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
//	public User findByUuidUser(String uuid);

}
