package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, String> {
	
	public List<Driver> findByDriverID(String driverID);

	public List<Driver> findAll();
}
