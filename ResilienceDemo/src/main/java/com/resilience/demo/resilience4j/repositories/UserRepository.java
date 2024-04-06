package com.resilience.demo.resilience4j.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resilience.demo.resilience4j.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
