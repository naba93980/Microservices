package com.hotel.ratingservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.hotel.ratingservice.entities.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    public List<Rating> findByUserId(Long userId);

    public List<Rating> findByHotelId(Long hotelId);
    
}
