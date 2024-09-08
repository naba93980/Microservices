package com.hotel.userservice.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating implements Serializable {

    private String ratingId;
    private Long userId;
    private Long hotelId;
    private Hotel hotel;
    private int rating;
    private String feedback;
}
