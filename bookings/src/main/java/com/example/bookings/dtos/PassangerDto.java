package com.example.bookings.dtos;

public class PassangerDto {
    private String name;
    private String seat;

    public PassangerDto(){}
    public PassangerDto(String name,String seat){
        this.name=name;
        this.seat=seat;
    }

    public String getName() {
        return name;
    }


    public String getSeat() {
        return seat;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
