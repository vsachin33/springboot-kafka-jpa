package com.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.DriverMapContainer;
import com.springboot.app.model.Employee;
import com.springboot.app.model.Store;
import com.springboot.app.repository.StoreRepository;
import com.springboot.app.service.Producer;
import com.springboot.service.DistanceCalculator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class AppController {

    @Autowired
    StoreRepository repository;

    @Autowired
    private Producer producer;

    // URL - http://localhost:9000/api/send
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping(value = "/send")
    public void send(@RequestBody final Employee employee) {
        log.info("Sending message to kafka topic");
        producer.sendMessageToTopic(employee.toString());
    }

    // URL - http://localhost:9000/api/updateDriver
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping(value = "/updateDriver")
    public void updateDriver(@RequestBody final String json) {
        log.info("Sending message to kafka topic");
        producer.sendMessageToDriverTopic(json);
    }

    // URL - http://localhost:9000/api/addStore
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping(value = "/addStore")
    public void addStore(@RequestBody final Store store) {
        log.info(store + " has been received.");
        repository.save(store);
    }
    
    // URL - http://localhost:9000/api/getDrivers
    @GetMapping("/getDrivers")
    @ResponseBody
    public String[] getDriver(@RequestParam String storeId, @RequestParam int N) {
        Store currStore = repository.findByStoreID(storeId).get(0);
        long t1 = System.currentTimeMillis();
    	String[] result = DistanceCalculator.findDrivers(N, DriverMapContainer.getInstance().getDistanceMap(currStore));
        log.info((System.currentTimeMillis() - t1)/1000.0 + "");

        return result;
    }
}
