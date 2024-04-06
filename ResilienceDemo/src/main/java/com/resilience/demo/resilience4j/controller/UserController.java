package com.resilience.demo.resilience4j.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resilience.demo.resilience4j.entities.User;
import com.resilience.demo.resilience4j.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam Long userId){
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User users = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId){
        String users = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    
}
