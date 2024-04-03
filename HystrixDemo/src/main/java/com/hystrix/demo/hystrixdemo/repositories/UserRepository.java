package com.hystrix.demo.hystrixdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hystrix.demo.hystrixdemo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
