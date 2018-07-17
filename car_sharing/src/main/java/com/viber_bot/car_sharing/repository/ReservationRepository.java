package com.viber_bot.car_sharing.repository;

import com.viber_bot.car_sharing.model.Reservation;
import com.viber_bot.car_sharing.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//    @Query("select r from reservation r where r.users.viberId = :viberId")
    public Iterable<Reservation> getByUser(@Param("viberId") String viberId);

    public Reservation findByRoute(Route route);

    public Reservation findById(int id);

}
