package com.example.bookings.dtos.client;

public class Seat {
    private Long id;
    private String seat;
    private Status status;

    public Seat() {}


    public Seat(Long id, String seat, Status status) {
        this.id = id;
        this.seat = seat;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

