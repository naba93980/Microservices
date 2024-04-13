package com.hotel.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Hotel;
import com.hotel.userservice.service.fallback.HotelServiceFallback;

@FeignClient(name = "Hotel-Service", fallback = HotelServiceFallback.class)
public interface HotelService {

    @GetMapping("/hotels/getHotel")
    // @CircuitBreaker(name = "hotelService")
    Hotel getHotel(@RequestParam Long hotelId);

}
