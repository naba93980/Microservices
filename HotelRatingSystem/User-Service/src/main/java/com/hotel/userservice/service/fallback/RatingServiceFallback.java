package com.hotel.userservice.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Rating;

@Component
public class RatingServiceFallback {

    public static List<Rating> getRatingByUserId(Long userId) {
        return List.of(
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback retry").build(),
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback").build(),
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback").build()
            );
    }

}
