package com.hotel.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotelservice.entities.Hotel;

public interface HotelRepositoy extends JpaRepository<Hotel, Long>{
    
}
