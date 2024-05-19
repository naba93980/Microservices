package com.hotel.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Hotel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "Hotel-Service")
public interface HotelService {

    @GetMapping("/hotels/getHotel")
    // @Retry(name = "hotelService") // 3rd to evaluate
    @Retry(name = "hotelService", fallbackMethod = "getHotelRetryFallback")

    // @CircuitBreaker(name = "hotelService", fallbackMethod = "getHotelCBFallback") // 2nd to evaluate
    @CircuitBreaker(name = "hotelService")
    
    @RateLimiter(name = "hotelService") // 1st to evaluate
    Hotel getHotel(@RequestParam Long hotelId);

    default Hotel getHotelCBFallback(Long hotelId, Exception e) {
        return Hotel.builder().hotelId(hotelId).name("Hotel not found cb").location("Location not found").about("About not found").build();
    }

    default Hotel getHotelRetryFallback(Long hotelId, Exception e) {
        return Hotel.builder().hotelId(hotelId).name("Hotel not found retry").location("Location not found").about("About not found").build();
    }

}
