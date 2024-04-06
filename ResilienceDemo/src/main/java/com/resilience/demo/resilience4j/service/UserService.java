package com.resilience.demo.resilience4j.service;

import java.util.List;

import com.resilience.demo.resilience4j.entities.User;

public interface UserService {
    
    User createUser(User user);

    User getUser(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    String deleteUser(Long userId);
     
}
