package com.hystrix.demo.hystrixdemo.error;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

