package com.hotel.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class UserServiceApplication {

	
	/* @Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	} */

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
