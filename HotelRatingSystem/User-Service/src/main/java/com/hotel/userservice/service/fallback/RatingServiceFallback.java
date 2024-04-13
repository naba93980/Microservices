package com.hotel.userservice.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hotel.userservice.entities.Rating;
import com.hotel.userservice.external.services.RatingService;

@Component
public class RatingServiceFallback implements RatingService {

    @Override
    public List<Rating> getRatingByUserId(Long userId) {
        return List.of(
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback").build(),
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback").build(),
            Rating.builder().ratingId("0").userId(userId).hotelId(0L).rating(0).feedback("no feedback").build()
            );
    }

}
