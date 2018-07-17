package com.viber_bot.car_sharing.repository;

import com.viber_bot.car_sharing.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    public Route findById(int id);

    public List<Route> findAllByViberId(String viberId);
}
