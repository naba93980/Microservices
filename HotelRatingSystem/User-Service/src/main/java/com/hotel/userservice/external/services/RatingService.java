package com.hotel.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Rating;

@FeignClient(name = "Rating-Service")
public interface RatingService {
    
    @GetMapping("/ratings/getRatingByUserId")
    List<Rating> getRatingByUserId(@RequestParam Long userId);
}
