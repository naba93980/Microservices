package com.hystrix.demo.HystrixDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hystrix.demo.HystrixDemo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
