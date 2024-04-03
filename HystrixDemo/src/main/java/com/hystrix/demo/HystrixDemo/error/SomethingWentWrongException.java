package com.hystrix.demo.HystrixDemo.error;

public class SomethingWentWrongException extends RuntimeException {
    
    public SomethingWentWrongException(String message) {
        super(message);
    }
}
