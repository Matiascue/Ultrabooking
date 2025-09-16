package com.example.bookings.services;

import com.example.bookings.dtos.client.FlightDto;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
    ResponseEntity<FlightDto>getFlightById(String id);
    ResponseEntity<FlightDto> postFlight(FlightDto flightDto);
}
