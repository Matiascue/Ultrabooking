package com.example.bookings.entity;

import com.example.bookings.models.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookingEntity {
    @Id
    private String id;
    private Status status;
    private String flight;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PassangerEntity> passengers;

    public BookingEntity(){}
    public BookingEntity(String id,Status status,String flight,List<PassangerEntity>passengers){
        this.id=id;
        this.status=status;
        this.flight=flight;
        this.passengers=passengers;
    }

    public List<PassangerEntity> getPassengers() {
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

    public void setPassengers(List<PassangerEntity> passengers) {
        this.passengers = passengers;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
