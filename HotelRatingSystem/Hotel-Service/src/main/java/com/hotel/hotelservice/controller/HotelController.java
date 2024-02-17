package com.hotel.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.service.HotelService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/hotels")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {
    
    private HotelService hotelservice;

    @PostMapping("/createHotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createdHotel = hotelservice.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping("/getHotel")
    public ResponseEntity<Hotel> getHotel(@RequestParam Long hotelId){
        Hotel hotel = hotelservice.getHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotels = hotelservice.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }

    @PostMapping("/updateHotel")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel){
        Hotel hotels = hotelservice.updateHotel(hotel);
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }

    @GetMapping("/deleteHotel")
    public ResponseEntity<String> deleteHotel(@RequestParam Long hotelId){
        String hotels = hotelservice.deleteHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }
}

