package com.hotel.hotelservice.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.error.ResourceNotFoundException;
import com.hotel.hotelservice.error.SomethingWentWrongException;
import com.hotel.hotelservice.repository.HotelRepositoy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
@CacheConfig(cacheNames = "hotels")
public class HotelServiceImplementation implements HotelService {

  HotelRepositoy hotelRepositoy;

  @Override
  public Hotel createHotel(Hotel hotel) {
    try {
      return hotelRepositoy.save(hotel);
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not create");
    }
  }

  @Override
  @Cacheable(key = "#hotelId")
  public Hotel getHotel(Long hotelId) {
    return hotelRepositoy.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("No such Hotel found"));
  }

  @Override
  @Cacheable(key = "'allHotels'")
  public List<Hotel> getAllHotels() {
    try {
      return hotelRepositoy.findAll();
    } catch (RuntimeException e) {
      throw new ResourceNotFoundException("Something went wrong, could not fetch");
    }
  }

  @Override
  @CachePut(key = "#hotel.hotelId")
  public Hotel updateHotel(Hotel hotel) {
    Hotel existingHotel = hotelRepositoy.findById(hotel.getHotelId())
        .orElseThrow(() -> new ResourceNotFoundException("No such Hotel found"));
    try {
      BeanUtils.copyProperties(hotel, existingHotel);
      return hotelRepositoy.save(existingHotel);
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not update");
    }
  }

  @Override
  @CacheEvict(key = "#hotelId")
  public String deleteHotel(Long hotelId) {
    try {
      hotelRepositoy.deleteById(hotelId);
      return "Deletion successful";
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not delete");
    }
  }

}
