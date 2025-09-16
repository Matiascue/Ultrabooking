package com.flights.flights.services.imp;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.newDtos.AirportNewDto;
import com.flights.flights.entity.AirportEntity;
import com.flights.flights.models.Airport;
import com.flights.flights.repository.AirportRepository;
import com.flights.flights.services.AirportService;
import com.flights.flights.services.GenericMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImp implements AirportService {
    @Autowired
    private GenericMapperService genericMapperService;
    @Autowired
    private AirportRepository airportRepository;

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImp.class);

    @Override
    public AirportDto addAirport(AirportNewDto airportNewDto) {
        Airport airport=this.genericMapperService.map(airportNewDto, Airport.class);
        airport.setActive(true);
        AirportEntity airportEntity=this.genericMapperService.map(airport, AirportEntity.class);
        return this.genericMapperService.map(airportRepository.save(airportEntity),AirportDto.class);
    }

    @Override
    public AirportDto getAirportById(Long id) {
        AirportEntity airportEntity=airportRepository.findById(id).orElseThrow(()->{
            logger.error("Airport not found with id: {}", id);
            return new RuntimeException("Airport not found");
        });
        return genericMapperService.map(airportEntity, AirportDto.class);
    }

    @Override
    public List<AirportDto> getAll() {
        return airportRepository.findAll().stream()
                .map(airportEntity -> genericMapperService.map(airportEntity, AirportDto.class))
                .toList();
    }

    @Override
    public AirportDto updateAirport(AirportDto airportDto) {
        AirportEntity airportEntity=airportRepository.findById(airportDto.getId()).orElseThrow(()->{
            logger.error("Airport not found with id: {}", airportDto.getId());
            return new RuntimeException("Airport not found");
        });
        airportEntity.setName(airportDto.getName());
        airportEntity.setCode(airportDto.getCode());
        airportEntity.setLocation(airportDto.getLocation());
        return this.genericMapperService.map(airportRepository.save(airportEntity),AirportDto.class);
    }

    @Override
    public String airportDown(Long id) {
        AirportEntity airportEntity=airportRepository.findById(id).orElseThrow(()->{
            logger.error("Airport not found with id: {}", id);
            return new RuntimeException("Airport not found");
        });
        airportEntity.setActive(false);
        this.airportRepository.save(airportEntity);
        return "The airport is down";
    }
}
