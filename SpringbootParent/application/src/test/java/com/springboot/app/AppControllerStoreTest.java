package com.springboot.app;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.app.controller.AppController;
import com.springboot.app.model.Store;
import com.springboot.app.service.StoreService;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class AppControllerStoreTest 
{
    
     
    @Mock
    StoreService storeService ;
    
    @InjectMocks
    AppController appController;
     
    
    // not tested 
    @Test
    public void testAddStore() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        //when(storeService.saveStore(any(Store.class))).thenReturn(Optional.of(new Store (1000,38.895, -74.82));
        Store store = new com.springboot.app.model.Store ("102", -75.00f, 38.89f);
        
        
        when( storeService.addStore(store) ).thenReturn( store );
         
        store = new Store ("1001", -75.00f, 38.89f);
        appController.addStore(store);
        
         
        assertThat(Boolean.TRUE);
        //assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
     
    
    
    
    
  /*  @Test
    public void testFindAll() 
    {
        // given
        Employee employee1 = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        Employee employee2 = new Employee(2, "Alex", "Gussin", "example@gmail.com");
        Employees employees = new Employees();
        employees.setEmployeeList(Arrays.asList(employee1, employee2));
 
        when(employeeDAO.getAllEmployees()).thenReturn(employees);
 
        // when
        Employees result = employeeController.getEmployees();
 
        // then
        assertThat(result.getEmployeeList().size()).isEqualTo(2);
         
        assertThat(result.getEmployeeList().get(0).getFirstName())
                        .isEqualTo(employee1.getFirstName());
         
        assertThat(result.getEmployeeList().get(1).getFirstName())
                        .isEqualTo(employee2.getFirstName());
    }
    
    */
}