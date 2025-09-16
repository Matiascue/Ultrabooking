package com.flights.flights.services;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.FlightDto;
import com.flights.flights.dtos.FlightInfoDto;

import java.util.List;

public interface FlightService {
    FlightInfoDto getFlightById(String id);
    FlightInfoDto postFlight(FlightDto flightDto);
    List<FlightInfoDto> getAll();
}
