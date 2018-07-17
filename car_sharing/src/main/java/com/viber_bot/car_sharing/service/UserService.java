package com.viber_bot.car_sharing.service;

import com.viber_bot.car_sharing.model.User;
import com.viber_bot.car_sharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void add(User user) {

        User userExists = userRepository.findByViberId(user.getViberId());

        if(userExists == null) {
            user.setSubscribe(true);
            userRepository.save(user);
        }
        else{
            subscribe(user.getViberId());
        }
    }

    @Transactional
    public void subscribe(String viberId) {
        User user = userRepository.findByViberId(viberId);

        if(user != null) {
            user.setSubscribe(true);
            userRepository.save(user);
        }
    }

    @Transactional
    public void unsubscribe(String viberId) {
        User user = userRepository.findByViberId(viberId);

        if(user != null) {
            user.setSubscribe(false);
            userRepository.save(user);
        }
    }

    public boolean findByViberId(String viberId) {
        if(userRepository.findByViberId(viberId) != null)
            return true;

        return false;
    }

    public User findUser(String viberId) {
        return userRepository.findByViberId(viberId);
    }
}
