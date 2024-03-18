package com.hotel.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service",r -> r.host("localhost").and().path("/users/**")
						.filters(f -> f.addResponseHeader("Server", "Netty"))
						.uri("lb://User-Service"))
				.route("hotel-service",r -> r.host("localhost").and().path("/hotels/**")
						.filters(f -> f.addResponseHeader("Server", "Netty"))
						.uri("lb://Hotel-Service"))
				.build();
	}

}
