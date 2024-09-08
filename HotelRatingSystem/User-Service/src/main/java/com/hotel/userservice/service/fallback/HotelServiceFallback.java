package com.hotel.userservice.service.fallback;

import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Hotel;

@Component
public class HotelServiceFallback {

    public static Hotel getHotelFallback(Long hotelId) {
        return Hotel.builder().hotelId(hotelId).name("Hotel not found retry").location("Location not found").about("About not found").build();
    }
}
