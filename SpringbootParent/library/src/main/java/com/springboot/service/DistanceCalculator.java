package com.springboot.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


@Service
@Slf4j

public class DistanceCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String[] findDrivers(int N, HashMap<String, Float> distances) {
		ArrayList<ArrayList<Object>> data = new ArrayList<>();

		for (String driverId : distances.keySet()) {
			ArrayList<Object> row = new ArrayList<>();

			data.add(row);

			row.add(driverId);
			row.add(distances.get(driverId));
		}

		Collections.sort(data, (a, b) -> Float.compare((Float) a.get(1), (Float) b.get(1)));

		String[] closestDrivers = new String[N];
		for (int i = 0; i < N; i++) {
			closestDrivers[i] = (String) data.get(i).get(0);
		}

		return closestDrivers;
	}

}
