package com.example.employeemgmt;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeemgmtApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	public EmployeemgmtApplication(@Value("${spring.profiles.active}") String activeProfile) {
		System.out.println("Running on "+ activeProfile + " environment");
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeemgmtApplication.class, args);
	}

}
