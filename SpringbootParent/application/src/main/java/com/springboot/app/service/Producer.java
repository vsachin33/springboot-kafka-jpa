package com.springboot.app.service;

import com.springboot.app.model.Driver;
import com.springboot.app.service.DriverMapContainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    // reading the property from the application.yml file
    // if value is not specified it will pick up the default value as "employees"
    @Value("${kafka.topic.name:employees}")
    private String topic;

    @Value("${kafka.topic.name2:driver_location}")
    private String driverTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToTopic(final String message) {
        log.info("Sending message to kafka = {}", message);
        kafkaTemplate.send(topic, message);
    }

    public void sendMessageToDriverTopic(final String message) {
        log.info("Sending message to kafka = {}", message);
        /* 
        long t1 = System.currentTimeMillis();
        for (int lat = -20; lat <= 20; lat++) {
            for (int lon = -20; lon <= 20; lon++) {
                kafkaTemplate.send(driverTopic, message);
            }
            log.info(lat + "");
        }
        log.info("Produce time: " + (System.currentTimeMillis() - t1)/1000.0 + "");
        */
        kafkaTemplate.send(driverTopic, message);
    }
}
