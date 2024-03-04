package com.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("db")
// @Profile("derfault")
@Profile("dev")
public class DBSettings {

    private String connection;
    private String host;
    private String port;

}
