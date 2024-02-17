package com.hotel.hotelservice.error;

public class SomethingWentWrongException extends RuntimeException {
    
    public SomethingWentWrongException(String message) {
        super(message);
    }
}
