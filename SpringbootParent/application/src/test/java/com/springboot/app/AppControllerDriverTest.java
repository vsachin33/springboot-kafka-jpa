package com.springboot.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.controller.AppController;
import com.springboot.app.model.Driver;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class AppControllerDriverTest{

	@Value("${server.port}")
	private int port;
	
	@Autowired
	AppController appController;
	
	

	//TestRestTemplate restTemplate = new TestRestTemplate();

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
			
	
	
     
	@Test
	@Order(1) 
	public void contextLoads() throws Exception {
		assertThat(appController).isNotNull();
	}
	

	 @Test
	 @Order(2) 
	 public void testUpdateDriver() {
		Driver driver = new Driver("ghg@gmail.com", 37.76f, -72.96f);
        
		String jsonString ="";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			jsonString = mapper.writeValueAsString(driver);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println(jsonString);

	      
		String uri = createURLWithPort("/api/updateDriver");
        System.out.println("uri for add driver test " + uri);  
		
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Void responseEntity = getRestTemplate().postForObject(uri,jsonString, Void.class);
		
    
     } 
	


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
