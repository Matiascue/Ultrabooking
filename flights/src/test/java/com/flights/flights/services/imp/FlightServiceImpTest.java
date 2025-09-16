package com.flights.flights.services.imp;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.FlightDto;
import com.flights.flights.dtos.FlightInfoDto;
import com.flights.flights.entity.AirportEntity;
import com.flights.flights.entity.FlightEntity;
import com.flights.flights.entity.SeatEntity;
import com.flights.flights.models.Airport;
import com.flights.flights.models.Seat;
import com.flights.flights.models.Status;
import com.flights.flights.repository.FlightRepository;
import com.flights.flights.services.AirportService;
import com.flights.flights.services.GenericMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FlightServiceImpTest {
    @InjectMocks
    private FlightServiceImp flightServices;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private GenericMapperService genericMapperService;
    @Mock
    private AirportService airportService;



    private FlightDto fly;
    private AirportEntity airport;
    private SeatEntity seatEntity;
    private FlightEntity flight;
    private FlightDto f1;
    private AirportDto airportt;
    private FlightInfoDto flightInfoDto;
    @BeforeEach
    public void setUp(){
        airport=new AirportEntity();
        airport.setId(1L);
        airport.setCode("AAB");
        airport.setName("Abcb");
        airport.setLocation("Argentina Buenos Aires");

        seatEntity=new SeatEntity();
        seatEntity.setId(1L);
        seatEntity.setSeat("A1");
        seatEntity.setStatus(Status.AVAIBLE);

        List<SeatEntity> seatEntityList=new ArrayList<>();
        seatEntityList.add(seatEntity);

        flight=new FlightEntity();
        flight.setId("AAA123");
        flight.setAirport(airport);
        flight.setDeparture(LocalDateTime.now().plusHours(6));
        flight.setAircraft("aricraft");
        flight.setSeat_map(seatEntityList);

        f1=new FlightDto();
        f1.setId("AAA123");
        f1.setAircraft("aricraft");

        airportt=new AirportDto();
        airportt.setId(1L);
        airportt.setCode("AAB");
        airportt.setName("Abcb");
        airportt.setLocation("Argentina Buenos Aires");

        Seat seat=new Seat();
        seat.setSeat("A1");
        seat.setStatus(Status.AVAIBLE);

        List<Seat> seatList=new ArrayList<>();
        seatList.add(seat);

        fly =new FlightDto();
        fly.setId("AAA123");
        fly.setAirport(1L);
        fly.setDeparture(LocalDateTime.now().plusHours(6).plusDays(1));
        fly.setAircraft("aricraft");
        fly.setSeat_map(seatList);

        flightInfoDto=new FlightInfoDto();
        flightInfoDto.setId("AAA123");
        flightInfoDto.setAirport(airportt);
        flightInfoDto.setDeparture(LocalDateTime.now().plusHours(6).plusDays(1));
        flightInfoDto.setAircraft("aricraft");
        flightInfoDto.setSeat_map(seatList);
    }
    @Test
    void getFlightById() {
        String aircraft="aricraft";
        Mockito.when(flightRepository.findById("AAA123")).thenReturn(Optional.of(flight));
        Mockito.when(genericMapperService.map(flight, FlightInfoDto.class)).thenReturn(flightInfoDto);
        FlightInfoDto flightDto=this.flightServices.getFlightById("AAA123");
        Assertions.assertNotNull(flightDto);
        Assertions.assertEquals("AAA123",flightDto.getId());
        Assertions.assertEquals(aircraft,flightDto.getAircraft());
    }
    @Test
    void getByIdError(){
        RuntimeException response=assertThrows(RuntimeException.class,()->{
            this.flightServices.getFlightById("1234ASD");
        });
        Assertions.assertEquals("Flight not found",response.getMessage());
    }
    @Test
    void postFlight() {
        Mockito.when(airportService.getAirportById(1L)).thenReturn(airportt);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Mockito.when(genericMapperService.map(fly, FlightEntity.class)).thenReturn(flight);
        Mockito.when(genericMapperService.map(airportt, AirportEntity.class)).thenReturn(airport);
        Mockito.when(genericMapperService.map(flight, FlightInfoDto.class)).thenReturn(flightInfoDto);
        FlightInfoDto response=this.flightServices.postFlight(fly);
        String aircraft="aricraft";
        Assertions.assertNotNull(response);
        Assertions.assertEquals(aircraft,response.getAircraft());
        Assertions.assertEquals("AAA123",response.getId());
    }
}