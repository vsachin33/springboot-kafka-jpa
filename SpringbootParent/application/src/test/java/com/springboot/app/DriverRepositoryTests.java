package com.springboot.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration.WebFluxConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.springboot.app.model.Store;
import com.springboot.app.repository.StoreRepository;

 
@DataJpaTest


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@ContextConfiguration(classes = SpringbootaApp.class)





public class DriverRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private StoreRepository repository;
     
    @Test
    public void testSaveNewProduct() {
        entityManager.persist(new Store("store1", 10f, 10f));
                 
        
        Optional <Store> oStore = repository.findById("store1");
        
         
       // assertThat(oStore.isPresent());
        
        assertEquals(oStore.get().getStoreID(), "store1");
    }
}