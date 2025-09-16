package com.example.bookings.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PassangerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String seat;

    public PassangerEntity(){}
    public PassangerEntity(Long id,String name,String seat){
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
