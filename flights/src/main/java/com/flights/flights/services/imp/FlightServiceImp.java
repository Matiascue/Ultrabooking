package com.flights.flights.services.imp;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.FlightDto;
import com.flights.flights.dtos.FlightInfoDto;
import com.flights.flights.entity.AirportEntity;
import com.flights.flights.entity.FlightEntity;
import com.flights.flights.repository.FlightRepository;
import com.flights.flights.services.AirportService;
import com.flights.flights.services.FlightService;
import com.flights.flights.services.GenericMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImp implements FlightService {
    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImp.class);
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportService airportService;
    @Autowired
    private GenericMapperService genericMapperService;
    @Override
    public FlightInfoDto getFlightById(String id) {
        FlightEntity flightEntity=this.flightRepository.findById(id).orElseThrow(()->{
            logger.error("Flight not found with id: {}", id);
            return new RuntimeException("Flight not found");

        });
        return this.genericMapperService.map(flightEntity, FlightInfoDto.class);
    }

    @Override
    public FlightInfoDto postFlight(FlightDto flightDto) {
        if(flightDto.getDeparture().withHour(0).isBefore(LocalDateTime.now().withHour(0))) {
            this.validateHour(flightDto.getDeparture());
        }
        FlightEntity flightEntity=this.genericMapperService.map(flightDto, FlightEntity.class);
        AirportDto airport=this.airportService.getAirportById(flightDto.getAirport());
        flightEntity.setAirport(genericMapperService.map(airport, AirportEntity.class));
        return this.genericMapperService.map(flightRepository.save(flightEntity), FlightInfoDto.class);
    }

    @Override
    public List<FlightInfoDto> getAll() {
        return flightRepository.findAll().stream()
                .map(flightEntity -> genericMapperService.map(flightEntity, FlightInfoDto.class))
                .toList();
    }

    private void validateHour(LocalDateTime time){
        int hourNow=LocalDateTime.now().getHour();
        int hourTime=time.getHour();
        int diferential=hourTime-hourNow;
        if(diferential<=6){
            logger.error("Flight departure time must be at least 6 hours in the future");
            throw new IllegalArgumentException("Flight departure time must be at least 6 hours in the future");
        }
    }
}
