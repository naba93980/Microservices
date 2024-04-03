package com.hystrix.demo.hystrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class HystrixDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDemoApplication.class, args);
	}

}
