package com.springboot.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.app.repository.DriverRepository;

import com.springboot.app.model.Driver;

@Service
public class DriverService {
    
	
    @Autowired   
	DriverMapContainerService driverMapContainerService;
	protected static final ConcurrentHashMap<String, Driver> driverCacheMap = new ConcurrentHashMap<String, Driver>(); 
	
	@Autowired
    private DriverRepository repository;


    
    
    
 /**
 * @param driverID
 * @return
 */
public List <Driver> getDriverByID(String driverID) {
	   
	   Driver driver = driverMapContainerService.getDriverFromCache(driverID);

	   if (driver != null)
   	   {
   		   ArrayList<Driver> drivers = new ArrayList();
 		   drivers.add(driver);
   		   return drivers;
   	   } 
   	   
	   
	   return repository.findByDriverID(driverID);
   }

    
    public boolean deleteDriver(String driverID) {
        
    	// Only one object will be returned
    	List <Driver> driverList = repository.findByDriverID(driverID);
    	
    	if (driverList == null) {
    		    		
    		return false;
    	}
    	if(driverList.isEmpty())
    	{
    		return false;
    	}
    	 
    	Driver driver = driverList.get(0);
    	  if(driver == null) 
    	  {
    		
    		  return false;
    	  }
    	repository.delete(driver);
    	driverMapContainerService.removeDriverFromCache(driverID);
        return true;
    }
    
    
    
    
   
    
    
}

