package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Store;
import com.springboot.app.service.DriverMapContainerService;
import com.springboot.app.service.Producer;
import com.springboot.app.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class AppController {

    
    @Autowired
    private Producer producer;
    
    @Autowired
    private StoreService storeService;
    
    
    DriverMapContainerService driverMapContainerService = DriverMapContainerService.getInstance();
    

    

    // URL - http://localhost:9000/api/drivers
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping(value = "/drivers")
    public void updateDriver(@RequestBody final String json) {
        log.info("Sending message to kafka topic");
        producer.sendMessageToDriverTopic(json);
    }

    // URL - http://localhost:9000/api/stores
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/stores")
    public void addStore(@RequestBody final Store store) {
        log.info(store + " has been received.");
        storeService.addStore(store);
        
    }
        	
    
    @GetMapping("/drivers")
    @ResponseBody
    public ResponseEntity<String[]> getNearestDrivers(@RequestParam String storeId, @RequestParam int numberofdrivers) {
        //Store currStore = repository.findByStoreID(storeId).get(0);
    	String[] result = new String[1];;
    	int totalDrivers = driverMapContainerService.getCacheSize();
   

    	if (numberofdrivers > driverMapContainerService.getCacheSize()) {
        	result[0] = "Number of requested Drivers are less than available drivers " + totalDrivers;

    		return new ResponseEntity<>(
    				result, 
    		          HttpStatus.BAD_REQUEST); 
    	}
        
        
    	List <Store> storeList = storeService.getStoreByStoreID(storeId);
    	
    	if (storeList == null || storeList.isEmpty()) {
    		
        	result[0] = "Store " + storeId + " not found";

    		return new ResponseEntity<>(
    				result, 
    		          HttpStatus.BAD_REQUEST); 
    	}        // unique 
    	
    	
    
    	Store currStore = storeList.get(0);
    	
        long t1 = System.currentTimeMillis();
    	result = driverMapContainerService.findNearestDrivers(numberofdrivers, currStore);
    			//DistanceCalculator.findDrivers(N, driverMapContainerService.getDistanceMap(currStore));
        log.info((System.currentTimeMillis() - t1)/1000.0 + "");

        return new ResponseEntity<>(
        	      result , 
        	      HttpStatus.OK);
    
    }
    	
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/stores/{id}")
    void deleteStore(@PathVariable String id) {
       storeService.deleteStore(id);
    }
    
}
