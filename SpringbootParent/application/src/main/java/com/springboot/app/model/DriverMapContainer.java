package com.springboot.app.model;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DriverMapContainer {

    private ConcurrentHashMap<String, Driver> driverList;

    private static DriverMapContainer instance = null;

    DriverMapContainer() {
        driverList = new ConcurrentHashMap<>();
    }

    public static DriverMapContainer getInstance(){
        if (instance == null){
            instance = new DriverMapContainer();
        }
        Object o = new Object();

        return instance;
    }

    public void addDriver(Driver d) {
        driverList.put(d.getDriverID(), d);
    }

    public HashMap<String, Float> getDistanceMap(Store store) {
        HashMap<String, Float> distances = new HashMap<>();

        for (String driverId : driverList.keySet()) {
            Driver currDriver = driverList.get(driverId);

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
}
