package com.hotel.hotelservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotels_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long hotelId;
    private String name;
    private String location;
    private String about;
}
