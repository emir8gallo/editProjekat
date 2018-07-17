package com.viber_bot.car_sharing.controller;

import com.viber_bot.car_sharing.model.Route;
import com.viber_bot.car_sharing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class RouteController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = "routes", method = RequestMethod.GET)
    public String getRoutes(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "login";

        model.addAttribute("routes", routeService.findAll());

        return "routes";
    }

    @RequestMapping(value="/addRoute", method = RequestMethod.GET)
    public String addRouteGet(HttpSession session) {

        if(session.getAttribute("userId") == null)
            return "login";

        return "addRoute";
    }

    @RequestMapping(value="/addRoute", method = RequestMethod.POST)
    public String addRoutePost(@RequestParam(value = "start") String start,
                               @RequestParam(value = "destination") String destination,
                               @RequestParam(value = "date") String date,
                               @RequestParam(value = "time") String time,
                               @RequestParam(value = "availableSeats") Integer availableSeats, Model model,
                               HttpSession session) {


        if(session.getAttribute("userId") == null)
            return "login";

        Route route = new Route(start, destination, LocalDate.parse(date), LocalTime.parse(time),
                availableSeats);
        routeService.save(route);
        model.addAttribute("routes", routeService.findAll());

        return "routes";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRoute(@PathVariable int id, Model model, HttpSession session) {

        if(session.getAttribute("userId") == null)
            return "login";

        routeService.delete(id);

        model.addAttribute("routes", routeService.findAll());
        return "redirect:/routes";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRouteGet(@PathVariable int id, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "login";

        model.addAttribute("route", routeService.findById(id));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editRoutePost(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "start") String start,
                                @RequestParam(value = "destination") String destination,
                                @RequestParam(value = "date") String date,
                                @RequestParam(value = "time") String time,
                                @RequestParam(value = "availableSeats") Integer availableSeats,
                                Model model, HttpSession session) {

        if(session.getAttribute("userId") == null)
            return "login";

        routeService.edit(id, new Route(start,destination, LocalDate.parse(date),
                        LocalTime.parse(time), availableSeats));

        model.addAttribute("routes", routeService.findAll());

        return "redirect:/routes";
    }
}
