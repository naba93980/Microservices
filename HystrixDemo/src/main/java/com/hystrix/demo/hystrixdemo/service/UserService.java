package com.hystrix.demo.hystrixdemo.service;

import java.util.List;

import com.hystrix.demo.hystrixdemo.entities.User;

public interface UserService {
    
    User createUser(User user);

    User getUser(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    String deleteUser(Long userId);
     
}
