package com.hotel.hotelservice.service;

import java.util.List;

import com.hotel.hotelservice.entities.Hotel;

public interface HotelService {

    Hotel createHotel(Hotel user);

    Hotel getHotel(Long userId);

    List<Hotel> getAllHotels();

    Hotel updateHotel(Hotel user);

    String deleteHotel(Long userId);
}
