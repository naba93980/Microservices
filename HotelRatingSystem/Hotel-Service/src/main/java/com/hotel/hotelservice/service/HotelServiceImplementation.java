package com.hotel.hotelservice.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HotelServiceImplementation implements HotelService{

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
  public Hotel getHotel(Long userId) {
    return hotelRepositoy.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No such User found"));
  }

   @Override
  public List<Hotel> getAllHotels() {
    try {
      return hotelRepositoy.findAll();
    } catch (RuntimeException e) {
      throw new ResourceNotFoundException("Something went wrong, could not fetch");
    }
  }

  @Override
  public Hotel updateHotel(Hotel hotel) {
    Hotel existingUser = hotelRepositoy.findById(hotel.getHotelId()).orElseThrow(() -> new ResourceNotFoundException("No such Hotel found"));
    try {
      BeanUtils.copyProperties(hotel, existingUser);
      return hotelRepositoy.save(existingUser);
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not update");
    }
  }

  @Override
  public String deleteHotel(Long userId) {
    try {
      hotelRepositoy.deleteById(userId);
      return "Deletion successful";
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not delete");
    }
  }


   

  
    
}
