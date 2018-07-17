package com.viber_bot.car_sharing.service;

import com.viber_bot.car_sharing.model.Reservation;
import com.viber_bot.car_sharing.model.Route;
import com.viber_bot.car_sharing.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getByUser(String viberId) {
        return (List<Reservation>) reservationRepository.getByUser(viberId);

    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Reservation findById(int id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Reservation getReservationByRoute(Route route) {
        return reservationRepository.findByRoute(route);
    }
}
