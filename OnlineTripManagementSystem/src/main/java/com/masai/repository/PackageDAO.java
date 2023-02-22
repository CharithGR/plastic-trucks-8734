package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Package;

public interface PackageDAO extends JpaRepository<Package, Integer>{

}
