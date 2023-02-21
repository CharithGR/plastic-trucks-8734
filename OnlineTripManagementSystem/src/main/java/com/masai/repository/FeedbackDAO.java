package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{

}
