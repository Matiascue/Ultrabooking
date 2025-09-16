package com.flights.flights.services.imp;

import com.flights.flights.builder.GenericBuilder;
import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.newDtos.AirportNewDto;
import com.flights.flights.entity.AirportEntity;
import com.flights.flights.models.Airport;
import com.flights.flights.repository.AirportRepository;
import com.flights.flights.services.GenericMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AirportServiceImpTest {
    @InjectMocks
    private AirportServiceImp airportServiceImp;
    @Mock
    private AirportRepository airportRepository;
    @Mock
    private GenericMapperService genericMapperService;

    private AirportEntity airportEntity;
    private AirportDto airportDto;
    private Airport airport;
    private List<AirportEntity> airportEntityList;
    private AirportNewDto airportNewDto;
    @BeforeEach
    void setUp(){
        airportEntity=new GenericBuilder<>(AirportEntity.class).with(a->{
            a.setId(1L);
            a.setActive(true);
            a.setLocation("Cordoba");
            a.setName("CordobaAirline");
            a.setCode("CBALN");
        }).build();

        airportEntityList=new ArrayList<>();
        airportEntityList.add(airportEntity);

        airport=new GenericBuilder<>(Airport.class).with(a->{
            a.setId(1L);
            a.setActive(true);
            a.setLocation("Cordoba");
            a.setName("CordobaAirline");
            a.setCode("CBALN");
        }).build();

        airportDto=new GenericBuilder<>(AirportDto.class).with(a->{
            a.setId(1L);
            a.setLocation("Cordoba");
            a.setName("CordobaAirline");
            a.setCode("CBALN");
        }).build();

        airportNewDto=new GenericBuilder<>(AirportNewDto.class).with(a->{
            a.setLocation("Cordoba");
            a.setName("CordobaAirline");
            a.setCode("CBALN");
        }).build();
    }
    @Test
    void addAirport() {
        Mockito.when(airportRepository.save(airportEntity)).thenReturn(airportEntity);
        Mockito.when(genericMapperService.map(airportNewDto, Airport.class)).thenReturn(airport);
        Mockito.when(genericMapperService.map(airport, AirportEntity.class)).thenReturn(airportEntity);
        Mockito.when(genericMapperService.map(airportEntity, AirportDto.class)).thenReturn(airportDto);
        AirportDto response=airportServiceImp.addAirport(airportNewDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals(airportEntity.getCode(),response.getCode());
        Assertions.assertEquals(airportEntity.getLocation(),response.getLocation());
        Assertions.assertEquals(airportEntity.getName(),response.getName());

    }

    @Test
    void getAirportById() {
        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(airportEntity));
        Mockito.when(genericMapperService.map(airportEntity, AirportDto.class)).thenReturn(airportDto);
        AirportDto response=airportServiceImp.getAirportById(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L,response.getId());
        Assertions.assertEquals(airportEntity.getCode(),response.getCode());
        Assertions.assertEquals(airportEntity.getLocation(),response.getLocation());
        Assertions.assertEquals(airportEntity.getName(),response.getName());
    }
    @Test
    void getAirportByIdNull(){
        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            this.airportServiceImp.getAirportById(1L);
        });
        Assertions.assertEquals("Airport not found",response.getMessage());
    }
    @Test
    void getAll() {
        Mockito.when(airportRepository.findAll()).thenReturn(airportEntityList);
        Mockito.when(genericMapperService.map(airportEntity, AirportDto.class)).thenReturn(airportDto);
        List<AirportDto>response=airportServiceImp.getAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(airportEntityList.size(),response.size());
        Assertions.assertEquals(airportEntityList.get(0).getName(),response.get(0).getName());
    }

    @Test
    void updateAirport() {
    }

    @Test
    void airportDown() {
    }
}