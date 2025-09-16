package com.flights.flights.controllers;

import com.flights.flights.dtos.AirportDto;
import com.flights.flights.dtos.newDtos.AirportNewDto;
import com.flights.flights.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/airport")
@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<AirportDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getAirportById(id));
    }
    @GetMapping("/airportList")
    public ResponseEntity<List<AirportDto>>getAllAirports(){
        return ResponseEntity.ok(airportService.getAll());
    }
    @PostMapping("/addAirport")
    public ResponseEntity<AirportDto>addAirport(@RequestBody AirportNewDto airportNewDto){
        return ResponseEntity.ok(airportService.addAirport(airportNewDto));
    }
    @PutMapping("/updateAirport")
    public ResponseEntity<AirportDto>updateAirport(@RequestBody AirportDto airportDto){
        return ResponseEntity.ok(airportService.updateAirport(airportDto));
    }
    @DeleteMapping("/airportDown/{id}")
    public ResponseEntity<String>downAirport(@PathVariable Long id){
        return ResponseEntity.ok(airportService.airportDown(id));
    }
}
