package com.flights.flights.dtos.newDtos;


public class AirportNewDto {
    private String name;
    private String code;
    private String location;

    public AirportNewDto() {}


    public AirportNewDto(String name, String code, String location) {
        this.name = name;
        this.code = code;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
