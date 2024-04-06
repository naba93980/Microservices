package com.resilience.demo.resilience4j.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resilience.demo.resilience4j.entities.User;
import com.resilience.demo.resilience4j.error.ResourceNotFoundException;
import com.resilience.demo.resilience4j.error.SomethingWentWrongException;
import com.resilience.demo.resilience4j.repositories.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
  @CircuitBreaker(name = "getUserById", fallbackMethod = "getUserFallback")
  public User getUser(Long userId) {
    return userRepository.findById(userId).get();
  }
  
  public User getUserFallback(Long userId, Exception e) { 
    return new User(0L, "FallbackName", "FallbackEmail", "FallbackAbout");
  }

  @Override
  @CircuitBreaker(name = "getAllUsers", fallbackMethod = "getAllUsersFallback")
  public List<User> getAllUsers() {
      return userRepository.findAll();
  }
  
  public List<User> getAllUsersFallback(Exception e) {
    return Arrays.asList(
      new User(0L, "FallbackName1", "FallbackEmail1", "FallbackAbout1"),
      new User(0L, "FallbackName2", "FallbackEmail2", "FallbackAbout2"),
      new User(0L, "FallbackName3", "FallbackEmail3", "FallbackAbout3")
    );
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
