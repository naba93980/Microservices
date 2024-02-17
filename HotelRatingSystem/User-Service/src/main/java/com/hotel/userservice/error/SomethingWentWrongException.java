package com.hotel.userservice.error;

public class SomethingWentWrongException extends RuntimeException {
    
    public SomethingWentWrongException(String message) {
        super(message);
    }
}
