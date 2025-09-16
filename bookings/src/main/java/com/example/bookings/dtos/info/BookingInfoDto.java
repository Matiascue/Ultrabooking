package com.example.bookings.dtos.info;

import com.example.bookings.dtos.PassangerDto;
import com.example.bookings.models.Status;

import java.util.List;

public class BookingInfoDto {
    private String id;
    private Status status;
    private String flight;
    private List<PassangerInfoDto> passengers;

    public BookingInfoDto(){}
    public BookingInfoDto(String id,Status status,String flight,List<PassangerInfoDto>passengers){
        this.id=id;
        this.status=status;
        this.flight=flight;
        this.passengers=passengers;
    }

    public List<PassangerInfoDto> getPassengers() {
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

    public void setPassengers(List<PassangerInfoDto> passengers) {
        this.passengers = passengers;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
