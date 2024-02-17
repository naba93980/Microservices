package com.hotel.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.userservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
