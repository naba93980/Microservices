package com.hotel.userservice.service.fallback;

import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Hotel;
import com.hotel.userservice.external.services.HotelService;

@Component
public class HotelServiceFallback implements HotelService {

    @Override
    public Hotel getHotel(Long hotelId) {
        return Hotel.builder().hotelId(hotelId).name("Hotel not found").location("Location not found").about("About not found").build();
    }

}
