package com.flights.flights.dtos;


public class AirportDto {
    private Long id;
    private String name;
    private String code;
    private String location;

    public AirportDto() {}

    public AirportDto(Long id, String name, String code, String location) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
