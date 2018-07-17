package com.viber_bot.car_sharing.controller;

import com.viber_bot.car_sharing.service.ReservationService;
import okhttp3.internal.http1.Http1Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "reservations", method = RequestMethod.GET)
    public String getReservations(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "login";
        model.addAttribute("reservations", reservationService.findAll());

        return "reservations";
    }

    @RequestMapping(value = "/deleteReservation/{id}", method = RequestMethod.GET)
    public String deleteRoute(@PathVariable int id, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "login";

        reservationService.delete(reservationService.findById(id));

        model.addAttribute("routes", reservationService.findAll());
        return "redirect:/reservations";
    }
}
