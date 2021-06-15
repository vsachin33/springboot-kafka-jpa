package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.app.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, String> {
	
	public List<Driver> findByDriverID(String driverID);

	public List<Driver> findAll();
}
