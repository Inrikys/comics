package com.estudo.designpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesignpatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignpatternApplication.class, args);
	}

}
