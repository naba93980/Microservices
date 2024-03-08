package com.hotel.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    
    private Long hotelId;
    private String name;
    private String location;
    private String about;
}
