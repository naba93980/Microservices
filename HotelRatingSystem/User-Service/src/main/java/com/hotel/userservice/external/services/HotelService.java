package com.hotel.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Hotel;

@FeignClient(name = "Hotel-Service")
public interface HotelService {

    @GetMapping("/hotels/getHotel")
    Hotel getHotel(@RequestParam Long hotelId);
 
}
