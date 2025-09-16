package com.flights.flights.services;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.newDtos.AirportNewDto;

import java.util.List;

public interface AirportService {
    AirportDto addAirport(AirportNewDto airportNewDto);
    AirportDto getAirportById(Long id);
    List<AirportDto> getAll();
    AirportDto updateAirport(AirportDto airportDto);
    String airportDown(Long id);
}
