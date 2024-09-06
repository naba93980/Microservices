package com.hotel.ratingservice.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("ratings_collection")
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable{

    @Id
    private String ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
}
