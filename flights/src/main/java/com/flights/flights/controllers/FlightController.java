package com.flights.flights.controllers;

import com.flights.flights.dtos.FlightDto;
import com.flights.flights.dtos.FlightInfoDto;
import com.flights.flights.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/flights")
@RestController
public class FlightController {
    @Autowired
    private FlightService flightServices;

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightInfoDto> getFlightById(@PathVariable String id){
        return ResponseEntity.ok(flightServices.getFlightById(id));
    }
    @GetMapping("/flights")
    public ResponseEntity<List<FlightInfoDto>>getAll(){
        return ResponseEntity.ok(flightServices.getAll());
    }
    @PostMapping("/flights")
    public ResponseEntity<FlightInfoDto>postFlight(@RequestBody FlightDto flight){
        return ResponseEntity.ok(flightServices.postFlight(flight));
    }
}
