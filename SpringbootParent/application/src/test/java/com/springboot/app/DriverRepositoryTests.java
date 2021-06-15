package com.springboot.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.springboot.app.model.Store;
import com.springboot.app.repository.StoreRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DriverRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private StoreRepository repository;
     
    @Test
    public void testSaveNewProduct() {
        entityManager.persist(new Store("store1", 10f, 10f));
                 
        Optional <Store> oStore = repository.findById("store1");
        
         
        assertThat(oStore.isPresent());
    }
}