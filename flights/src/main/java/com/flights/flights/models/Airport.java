package com.flights.flights.models;


public class Airport {
    private Long id;
    private String name;
    private String code;
    private String location;
    private boolean active;

    public Airport() {}

    public Airport(Long id, String name, String code, String location, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.location = location;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
