package com.hystrix.demo.HystrixDemo.service;

import java.util.List;

import com.hystrix.demo.HystrixDemo.entities.User;

public interface UserService {
    
    User createUser(User user);

    User getUser(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    String deleteUser(Long userId);
     
}
