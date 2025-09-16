package com.example.bookings.models;



import java.util.List;

public class Booking {
    private String id;
    private Status status;
    private String flight;
    private List<Passanger> passengers;

    public Booking(){}
    public Booking(String id,Status status,String flight,List<Passanger>passengers){
        this.id=id;
        this.status=status;
        this.flight=flight;
        this.passengers=passengers;
    }

    public List<Passanger> getPassengers() {
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

    public void setPassengers(List<Passanger> passengers) {
        this.passengers = passengers;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
