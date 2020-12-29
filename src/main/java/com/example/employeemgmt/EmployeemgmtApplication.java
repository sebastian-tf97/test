package com.example.employeemgmt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeemgmtApplication {

	public EmployeemgmtApplication(@Value("${spring.profiles.active}") String activeProfile) {
		System.out.println("Running on "+ activeProfile + " environment");
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeemgmtApplication.class, args);
	}

}
