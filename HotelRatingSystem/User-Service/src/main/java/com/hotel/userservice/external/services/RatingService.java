package com.hotel.userservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Rating;
import com.hotel.userservice.service.fallback.RatingServiceFallback;

@FeignClient(name = "Rating-Service", fallback = RatingServiceFallback.class)
public interface RatingService {
    
    @GetMapping("/ratings/getRatingByUserId")
    List<Rating> getRatingByUserId(@RequestParam Long userId);
}
