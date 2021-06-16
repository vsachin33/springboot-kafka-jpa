package com.springboot.app;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.app.model.Driver;
import com.springboot.app.model.Store;
import com.springboot.app.service.DriverMapContainerService;
import com.springboot.app.service.DriverService;
import com.springboot.app.service.StoreService;





//@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverMapContainerServiceTest {

	@MockBean
	StoreService storeServiceMock;

	@MockBean
	DriverService driverServiceMock;
	
	@Autowired
	DriverMapContainerService driverMapContainerService;
	
	

	
	@Test
	public void testFindTheNearestDriverList() {
		

	    driverMapContainerService.addDriverToCache(new Driver("abc@gmail.com", -1.0f, 1.895f));
	    driverMapContainerService.addDriverToCache(new Driver("xyz@gmail.com", -76.03f, 38.895f));

	    //when(driverServiceMock.getDrivers()).thenReturn(driversList);
		
	    String [] result = driverMapContainerService.findNearestDrivers(1, new Store ("1000",1.0f, -1.0f) );
	    
	    String resultDriverId = "Not Found";
	    if (result.length == 1)
	    {
		 resultDriverId = result[0];
	    }
		
		assertEquals(resultDriverId,"abc@gmail.com");
	} 
	
	
	/*
	@Test
    public void testAddDriverWithConcurrency() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
        	    
            	String [] result = driverMapContainerService.findNearestDrivers(1, new Store ("1000",1.0f, -1.0f) );
                Thread.sleep(1000);
              
            });
        }
        latch.await();
       
        assertEquals(numberOfThreads, counter.getCount());
    }
	*/
	
	
}