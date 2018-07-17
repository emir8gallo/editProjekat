package com.viber_bot.car_sharing.repository;

import com.viber_bot.car_sharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByViberId(String viberId);

}
