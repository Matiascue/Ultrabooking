package com.flights.flights.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FlightEntity {
    @Id
    private String id;
    private String aircraft;
    private LocalDateTime departure;
    @ManyToOne
    private AirportEntity airport;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SeatEntity> seat_map;

    public FlightEntity() {}

    public FlightEntity(String id, String aircraft, LocalDateTime departure, AirportEntity airport, List<SeatEntity> seat_map) {
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

    public AirportEntity getAirport() {
        return airport;
    }

    public void setAirport(AirportEntity airport) {
        this.airport = airport;
    }

    public List<SeatEntity> getSeat_map() {
        return seat_map;
    }

    public void setSeat_map(List<SeatEntity> seat_map) {
        this.seat_map = seat_map;
    }
}
