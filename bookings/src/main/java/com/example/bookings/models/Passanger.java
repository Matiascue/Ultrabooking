package com.example.bookings.models;

public class Passanger {
    private Long id;
    private String name;
    private String seat;

    public Passanger(){}
    public Passanger(Long id,String name,String seat){
        this.id=id;
        this.name=name;
        this.seat=seat;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getSeat() {
        return seat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
