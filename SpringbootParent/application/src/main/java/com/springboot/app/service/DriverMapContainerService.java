package com.springboot.app.service;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.springboot.app.model.Driver;
import com.springboot.app.model.Store;
import com.springboot.service.DistanceCalculator;

@Service 
public class DriverMapContainerService {

    private ConcurrentHashMap<String, Driver> driverCacheMap;

    private static final Object lock = new Object();
    private static DriverMapContainerService instance = null;
    

    private DriverMapContainerService() {
        driverCacheMap = new ConcurrentHashMap<>();
    }

    public static DriverMapContainerService getInstance(){
       
    	DriverMapContainerService r = instance;
    	if (r == null) {
            synchronized (lock) {    // While we were waiting for the lock, another 
                r = instance;        // thread may have instantiated the object.
                if (r == null) {  
                    r = new DriverMapContainerService();
                    instance = r;
                }
            }
        }
    	
    	if (instance == null){
            instance = new DriverMapContainerService();
        }
        

        return instance;
    }

    public void addDriverToCache(Driver d) {
        driverCacheMap.put(d.getDriverID(), d);
    }

    public String[] findNearestDrivers(int numberOfDrivers, Store currStore )
    {
    	
    	String[] result = DistanceCalculator.findDrivers(numberOfDrivers, getDistanceMap(currStore));
        return result;
    	
    }
    
    
    public HashMap<String, Float> getDistanceMap(Store store) {
        HashMap<String, Float> distances = new HashMap<>();

        for (String driverId : driverCacheMap.keySet()) {
            Driver currDriver = driverCacheMap.get(driverId);

            float longitude = 0;
            float latitude = 0;
            synchronized (currDriver) {
                latitude = currDriver.getLatitude();
                longitude = currDriver.getLongitude();
            }

            float distance = (float) Math.sqrt(Math.pow(store.getLatitude() - latitude, 2)
                    + Math.pow(store.getLongitude() - longitude, 2));

            distances.put(driverId, distance);

        }

        return distances;
    }
    
    
    public int getCacheSize() {
    	return driverCacheMap.size(); 	
    }
    
    
    
}
