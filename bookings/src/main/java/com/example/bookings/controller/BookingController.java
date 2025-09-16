package com.example.bookings.controller;

import com.example.bookings.dtos.BookingDto;
import com.example.bookings.dtos.info.BookingInfoDto;
import com.example.bookings.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/reservation/{id}")
    public ResponseEntity<BookingInfoDto>getById(@PathVariable String id){
        return ResponseEntity.ok(bookingService.getReservation(id));
    }
    @PostMapping("/reservation")
    public ResponseEntity<BookingDto>newBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.postReservation(bookingDto));
    }
    @PutMapping("/checkIn")
    public ResponseEntity<BookingDto>checkIn(@RequestBody BookingInfoDto bookingDto){
        return ResponseEntity.ok(bookingService.checkIn(bookingDto));
    }
}
