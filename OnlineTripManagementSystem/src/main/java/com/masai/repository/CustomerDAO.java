package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer>{

}
