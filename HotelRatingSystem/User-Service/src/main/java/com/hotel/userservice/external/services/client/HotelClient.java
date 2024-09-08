package com.hotel.userservice.external.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Hotel;
import com.hotel.userservice.service.fallback.HotelServiceFallback;
import com.hotel.userservice.utils.CustomCache;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "Hotel-Service")
public interface HotelClient {

    @GetMapping("/hotels/getHotel")
    @Retry(name = "hotelService", fallbackMethod = "getHotelRetryFallback")
    @CircuitBreaker(name = "hotelService")
    @RateLimiter(name = "hotelService")
    Hotel getHotel(@RequestParam Long hotelId);

    default Hotel getHotelRetryFallback(Long hotelId, Exception e) {
        return HotelServiceFallback.getHotelFallback(hotelId);
    }

}
