package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.model.Store;

public interface StoreRepository extends CrudRepository<Store, String> {
	
	public List<Store> findByStoreID(String storeID);
}
