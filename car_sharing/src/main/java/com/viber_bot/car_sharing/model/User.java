package com.viber_bot.car_sharing.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="viber_id")
    private String viberId;

    @Column(name="name")
    private String name;

    @Column(name="subscribe")
    private boolean subscribe;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    public User() {
    }

    public User(String viberId, String name, boolean subscribe) {
        this.viberId = viberId;
        this.name = name;
        this.subscribe = subscribe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViberId() {
        return viberId;
    }

    public void setViberId(String viberId) {
        this.viberId = viberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }
}
