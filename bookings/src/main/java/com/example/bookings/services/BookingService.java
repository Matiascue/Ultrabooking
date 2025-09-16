package com.example.bookings.services;

import com.example.bookings.dtos.BookingDto;
import com.example.bookings.dtos.info.BookingInfoDto;

public interface BookingService {
    BookingDto postReservation(BookingDto reservation);
    BookingInfoDto getReservation(String id);
    BookingDto checkIn(BookingInfoDto reservation);
}
