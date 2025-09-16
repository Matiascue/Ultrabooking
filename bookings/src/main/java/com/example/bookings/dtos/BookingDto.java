package com.example.bookings.dtos;

import com.example.bookings.models.Passanger;
import com.example.bookings.models.Status;

import java.util.List;

public class BookingDto {
    private String id;
    private Status status;
    private String flight;
    private List<PassangerDto> passengers;

    public BookingDto(){}
    public BookingDto(String id,Status status,String flight,List<PassangerDto>passengers){
        this.id=id;
        this.status=status;
        this.flight=flight;
        this.passengers=passengers;
    }

    public List<PassangerDto> getPassengers() {
        return passengers;
    }

    public Status getStatus() {
        return status;
    }

    public String getFlight() {
        return flight;
    }

    public String getId() {
        return id;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassengers(List<PassangerDto> passengers) {
        this.passengers = passengers;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
