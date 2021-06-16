package com.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableSwagger2
public class SpringbootaApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootaApp.class, args);
		log.info("Springboot and kafka application is started successfully.");
	}
	
	@Bean
	public Docket productApi() {
	    return new Docket(DocumentationType.SWAGGER_2).select()
	      .apis(RequestHandlerSelectors.basePackage("com.springboot.app")).build();
	}
	  
}
