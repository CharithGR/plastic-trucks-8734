package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Report;

public interface ReportDAO extends JpaRepository<Report, Integer>{

}
