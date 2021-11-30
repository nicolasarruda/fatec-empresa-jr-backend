package com.empresajr.fatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.empresajr.fatec.entities")
@SpringBootApplication
public class FatecApplication {

	public static void main(String[] args) {
		SpringApplication.run(FatecApplication.class, args);
	}

}
