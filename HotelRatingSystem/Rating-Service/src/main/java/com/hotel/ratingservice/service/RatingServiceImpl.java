package com.hotel.ratingservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.ratingservice.entities.Rating;
import com.hotel.ratingservice.repositories.RatingRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
      return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(Long userId) {
     return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(Long hotelId) {
     return ratingRepository.findByHotelId(hotelId);
    }
    
}
