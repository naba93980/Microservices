package com.resilience.demo.resilience4j.error;

public class SomethingWentWrongException extends RuntimeException {
    
    public SomethingWentWrongException(String message) {
        super(message);
    }
}
