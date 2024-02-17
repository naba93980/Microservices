package com.hotel.userservice.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.userservice.entities.User;
import com.hotel.userservice.error.ResourceNotFoundException;
import com.hotel.userservice.error.SomethingWentWrongException;
import com.hotel.userservice.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImplementation implements UserService {

  UserRepository userRepository;

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
    return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No such User found"));
  }

  @Override
  public List<User> getAllUsers() {
    try {
      return userRepository.findAll();
    } catch (RuntimeException e) {
      throw new ResourceNotFoundException("Something went wrong, could not fetch");
    }
  }

  @Override
  public User updateUser(User user) {
    User existingUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("No such User found"));
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
