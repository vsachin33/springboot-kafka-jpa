package com.springboot.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Employee {

    UUID id;
    String firstname;
    String lastname;
    String jobtitle;
    String phone;

}
