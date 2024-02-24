package com.hotel.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.ratingservice.entities.Rating;
import com.hotel.ratingservice.service.RatingService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/ratings")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RatingController {
    
    private RatingService ratingService;

    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating createdRating = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.status(HttpStatus.OK).body(ratings);
    }

    @GetMapping("/getRatingByUserId")
    public List<Rating> getRatingByUserId(@RequestParam Long userId){
        return ratingService.getRatingByUserId(userId);
    }

    @GetMapping("/getRatingByHotelId")
    public List<Rating> getRatingByHotelId(@RequestParam Long hotelId){
        return ratingService.getRatingByHotelId(hotelId);
    }

}
