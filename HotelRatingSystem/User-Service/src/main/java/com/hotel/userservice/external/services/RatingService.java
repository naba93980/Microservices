package com.hotel.userservice.external.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Rating;
import com.hotel.userservice.external.services.client.RatingClient;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CacheConfig(cacheNames = "ratings")
public class RatingService {

    RatingClient ratingClient;

    @Cacheable(key = "#userId")
    public List<Rating> getRatingByUserId(Long userId) {
        return ratingClient.getRatingByUserId(userId);
    }
}
