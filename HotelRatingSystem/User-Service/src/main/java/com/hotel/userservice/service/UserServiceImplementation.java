package com.hotel.userservice.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


import com.hotel.userservice.entities.Hotel;
import com.hotel.userservice.entities.Rating;
import com.hotel.userservice.entities.User;
import com.hotel.userservice.error.ResourceNotFoundException;
import com.hotel.userservice.error.SomethingWentWrongException;
import com.hotel.userservice.external.services.HotelService;
import com.hotel.userservice.external.services.RatingService;
import com.hotel.userservice.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImplementation implements UserService {

  UserRepository userRepository;

  HotelService hotelService;

  RatingService ratingService;

  @Override
  public User createUser(User user) {
    try {
      return userRepository.save(user);
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not create");
    }
  }

  @Override
  public User getUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No such User found"));
    List<Rating> ratings = ratingService.getRatingByUserId(user.getUserId());
    ratings = ratings.stream().map(rating -> {
      Hotel hotel = hotelService.getHotel(rating.getHotelId());
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());
    user.setRatings(ratings);
    return user;
  }

  @Override
  public List<User> getAllUsers() {
    try {
      return userRepository.findAll().stream().map(user -> {
        List<Rating> ratings = ratingService.getRatingByUserId(user.getUserId());
        ratings = ratings.stream().map(rating -> {
          Hotel hotel = hotelService.getHotel(rating.getHotelId());
          rating.setHotel(hotel);
          return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratings);
        return user;
      }).collect(Collectors.toList());
    } catch (RuntimeException e) {
      throw new ResourceNotFoundException("Something went wrong, could not fetch");
    }
  }

  @Override
  public User updateUser(User user) {
    User existingUser = userRepository.findById(user.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("No such User found"));
    try {
      BeanUtils.copyProperties(user, existingUser);
      return userRepository.save(existingUser);
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not update");
    }
  }

  @Override
  public String deleteUser(Long userId) {
    try {
      userRepository.deleteById(userId);
      return "Deletion successful";
    } catch (RuntimeException e) {
      throw new SomethingWentWrongException("Something went wrong, could not delete");
    }
  }

}
