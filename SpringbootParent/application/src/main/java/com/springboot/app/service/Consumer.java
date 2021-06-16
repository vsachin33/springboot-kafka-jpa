package com.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.app.model.Driver;
import com.springboot.app.service.DriverMapContainerService;
import com.springboot.app.repository.DriverRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

    @Autowired
    DriverRepository repository;


    @KafkaListener(topics = "#{'${kafka.topic.name:driver_location}'}", groupId = "group_id")
    public void consumeDriver(final String message) {
        log.info("consumeDriver(): Consuming message.");
        log.info(message);
        try {
        	
        	Gson deserializer = new Gson();
        	Driver driver = deserializer.fromJson(message, Driver.class);
            // Put in cache 
            DriverMapContainerService.getInstance().addDriverToCache(driver);
            repository.save(driver);
        }
        catch(Exception e) {
        	//suppress exception
        }
    }
}
