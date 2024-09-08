package com.hotel.userservice.external.services.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.userservice.entities.Rating;
import com.hotel.userservice.service.fallback.RatingServiceFallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "Rating-Service")
public interface RatingClient {

    @GetMapping("/ratings/getRatingByUserId")
    @Retry(name = "ratingService", fallbackMethod = "getRatingByUserIdRetryFallback")
    @CircuitBreaker(name = "ratingService")
    @RateLimiter(name = "ratingService")
    List<Rating> getRatingByUserId(@RequestParam Long userId);

    default List<Rating> getRatingByUserIdRetryFallback(Long userId, Exception e) {
        return RatingServiceFallback.getRatingByUserId(userId);
    }

}
