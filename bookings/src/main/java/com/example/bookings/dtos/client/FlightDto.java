package com.example.bookings.dtos.client;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDto {
    private String id;
    private String aircraft;
    private LocalDateTime departure;
    private AirportDto airport;
    private List<Seat> seat_map;

    public FlightDto() {}


    public FlightDto(String id, String aircraft, LocalDateTime departure, AirportDto airport, List<Seat> seat_map) {
        this.id = id;
        this.aircraft = aircraft;
        this.departure = departure;
        this.airport = airport;
        this.seat_map = seat_map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public AirportDto getAirport() {
        return airport;
    }

    public void setAirport(AirportDto airport) {
        this.airport = airport;
    }

    public List<Seat> getSeat_map() {
        return seat_map;
    }

    public void setSeat_map(List<Seat> seat_map) {
        this.seat_map = seat_map;
    }
}
