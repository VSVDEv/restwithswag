package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;





@SpringBootApplication
@OpenAPIDefinition
public class RestwithswagApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestwithswagApplication.class, args);
	}

}
