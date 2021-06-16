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
    
	

	protected static final ConcurrentHashMap<String, Driver> driverCacheMap = new ConcurrentHashMap<String, Driver>(); 
	
	@Autowired
    private DriverRepository repository;



	
    public List<Driver> saveDrivers(List<Driver> drivers) {
    	return repository.saveAll(drivers);
    }

    
    //@Cacheable("driversCache")
    public List<Driver> getDrivers() {
    	if (!driverCacheMap.isEmpty())
    	{
    		List<Driver> cachedDrivers =  new ArrayList<Driver>(driverCacheMap.values());
    		return cachedDrivers;
    	}
    	List <Driver> drivers =  repository.findAll();
    	
    	for(int i = 0; i < drivers.size() ; i++) {
    		Driver driver = drivers.get(i);
    		driverCacheMap.put(driver.getDriverID(), driver);
    	}
    	
    	return drivers;
    }
    
    
    

    //@Cacheable("driversCache")
    //@CacheEvict(beforeInvocation = true)
    //@Cacheable
    
    
    public Driver saveDriver(Driver driver) {
        Driver savedDriver = repository.save(driver);
    	driverCacheMap.put(savedDriver.getDriverID(),savedDriver);
    	return savedDriver;
    }
    
   public List <Driver> getDriverByID(String driverID) {
	   
	   if (!driverCacheMap.isEmpty())
   	   {
   		   ArrayList drivers = new ArrayList();
		   
   		   drivers.add(driverCacheMap.get(driverID));
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
    	driverCacheMap.remove(driverID);
        return true;
    }
    
    
    @Scheduled(fixedRate = 60000)
    public void evictAllcachesAtIntervals() {
        driverCacheMap.clear();
    }
    
    
   
    
    
}

