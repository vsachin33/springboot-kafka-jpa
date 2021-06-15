package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springboot.app.model.Store;

public interface StoreRepository extends JpaRepository<Store, String> {
	
	public List<Store> findByStoreID(String storeID);
}
