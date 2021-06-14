package com.springboot.app.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Entity
public class Driver {

    @Id
    String driverID;
    @Getter
    float latitude;
    @Getter
    float longitude;
}
