package com.springboot.app;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.model.Driver;
import com.springboot.app.model.Store;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMockitoApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	
	
	
	@Test
	public void updateDriverTest() throws Exception {
        
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Driver driver = new Driver("ghg@gmail.com", 37.76f, -72.96f);
		String jsonRequest = om.writeValueAsString(driver);
		MvcResult result = mockMvc.perform(post("/api/updateDriver").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isAccepted()).andReturn();
		
		Assertions.assertTrue(Boolean.TRUE);

	}
	
	@Test
	public void addStoreTest() throws Exception {
        
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            
		
        Store store = new Store("5000", 30.76f, -72.96f);
		String jsonRequest = om.writeValueAsString(store);
		MvcResult result = mockMvc.perform(post("/api/addStore"
			).content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();
		
		Assertions.assertTrue(Boolean.TRUE);

	}


/*
	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("Basant");
		employee.setDept("IT");
		String jsonRequest = om.writeValueAsString(employee);
		MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isStatus() == Boolean.TRUE);

	}

	@Test
	public void getNearestDrivers() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/EmployeeService/getEmployees").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isStatus() == Boolean.TRUE);

	}
	*/

}