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
public class Hotel implements Serializable{
    
    private Long hotelId;
    private String name;
    private String location;
    private String about;
}
