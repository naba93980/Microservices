package com.hotel.userservice.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Hotel;
import com.hotel.userservice.external.services.client.HotelClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CacheConfig(cacheNames = "hotels")
public class HotelService {

    HotelClient hotelClient;

    //@CustomCache(key = "#hotelId", ttl1 = 10, ttl2 = 60)
    @Cacheable(key = "#hotelId")
    public Hotel getHotel(Long hotelId) {
        return hotelClient.getHotel(hotelId);
    }

}
