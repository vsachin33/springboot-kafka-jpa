package com.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Driver;
import com.springboot.app.service.DriverMapContainerService;
import com.springboot.app.repository.DriverRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataLoader {

    @Autowired
    public DataLoader(DriverRepository driverRepository) {
        List<Driver> databaseDrivers = driverRepository.findAll();

        for (Driver driver : databaseDrivers) {
            DriverMapContainerService.getInstance().addDriverToCache(driver);
        }
    }
}
