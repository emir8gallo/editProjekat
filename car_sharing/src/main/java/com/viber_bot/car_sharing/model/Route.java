package com.viber_bot.car_sharing.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name="start")
    private String start;

    @Column(name="destination")
    private String destination;

    @Column(name="date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name="available_seats")
    private int availableSeats;

    @Column(name = "viber_id")
    private String viberId;

    @OneToMany(mappedBy = "route")
    private List<Reservation> reservationList = new ArrayList<>();

    public Route() {
    }

    public Route(String start, String destination, LocalDate date, LocalTime time, int availableSeats) {
        this.start = start;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public Route(String start, String destination, LocalDate date, LocalTime time, int availableSeats, String viberId) {
        this.start = start;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
        this.viberId = viberId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getViberId() {
        return viberId;
    }

    public void setViberId(String viberId) {
        this.viberId = viberId;
    }
}
