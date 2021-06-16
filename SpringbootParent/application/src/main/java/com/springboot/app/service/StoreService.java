package com.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Store;
import com.springboot.app.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;

    public Store addStore(Store store) {
        return repository.save(store);
    }

    public List<Store> saveStores(List<Store> stores) {
        return repository.saveAll(stores);
    }
    
    

    public List<Store> getStores() {
        return repository.findAll();
    }

    
    public List<Store> getStoreByStoreID(String storeId) {
        return repository.findByStoreID(storeId);
    } 
    
    
    public void deleteStore(String id) {
       repository.deleteById(id);
        
    } 
    
    
}

   

    /*public String deleteStore(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    } 

    /*
    public Store updateStore(Store store) {
        Store existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }
    */

