package com.hotel.ratingservice.service;

import java.util.List;

import com.hotel.ratingservice.entities.Rating;

public interface RatingService {

  
    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingByUserId(Long userId);

    List<Rating> getRatingByHotelId(Long hotelId);

}
