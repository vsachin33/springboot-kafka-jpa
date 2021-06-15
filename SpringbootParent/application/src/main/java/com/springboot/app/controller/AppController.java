package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.service.DriverMapContainerService;
import com.springboot.app.model.Employee;
import com.springboot.app.model.Store;
import com.springboot.app.repository.StoreRepository;
import com.springboot.app.service.Producer;
import com.springboot.app.service.StoreService;
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
    
    @Autowired
    private StoreService storeService;
    
    
    DriverMapContainerService driverMapContainerService = DriverMapContainerService.getInstance();
    

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
    public String[] getNearestDrivers(@RequestParam String storeId, @RequestParam int N) {
        //Store currStore = repository.findByStoreID(storeId).get(0);
    	String[] emptyArrayOfDrivers = new String[1];
    	if (N > driverMapContainerService.getCacheSize())
        	return  emptyArrayOfDrivers;
        
        
    	List <Store> storeList = storeService.getStoreByStoreID(storeId);
        // unique 
    	Store currStore = storeList.get(0);
        long t1 = System.currentTimeMillis();
    	String[] result = DistanceCalculator.findDrivers(N, driverMapContainerService.getDistanceMap(currStore));
        log.info((System.currentTimeMillis() - t1)/1000.0 + "");

        return result;
    }
    
    @GetMapping("/getNearestDrivers")
    @ResponseBody
    public ResponseEntity<String[]> getNearestDrivers2(@RequestParam String storeId, @RequestParam int N) {
        //Store currStore = repository.findByStoreID(storeId).get(0);
    	String[] emptyArrayOfDrivers = new String[1];;
    	int totalDrivers = driverMapContainerService.getCacheSize();
   
    	emptyArrayOfDrivers[0] = "Number of requested Drivers are less than available drivers " + totalDrivers;

    	if (N > driverMapContainerService.getCacheSize())
    		return new ResponseEntity<>(
    				emptyArrayOfDrivers, 
    		          HttpStatus.BAD_REQUEST);  ;
        
        
    	List <Store> storeList = storeService.getStoreByStoreID(storeId);
        // unique 
    	Store currStore = storeList.get(0);
        long t1 = System.currentTimeMillis();
    	String[] result = DistanceCalculator.findDrivers(N, driverMapContainerService.getDistanceMap(currStore));
        log.info((System.currentTimeMillis() - t1)/1000.0 + "");

        return new ResponseEntity<>(
        	      result , 
        	      HttpStatus.OK);
    
    }
    	
    
    
    
}
