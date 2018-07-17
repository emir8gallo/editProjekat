package com.viber_bot.car_sharing.controller;

import com.viber_bot.car_sharing.model.Admin;
import com.viber_bot.car_sharing.model.User;
import com.viber_bot.car_sharing.repository.AdminRepository;
import com.viber_bot.car_sharing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RouteService routeService;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model, HttpSession session) {
        if(session.getAttribute("userId") != null) {
            return "redirect:/routes";
        }
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin (@RequestParam String username, @RequestParam String password,
                             HttpSession session, Model model) {
        System.out.println(username);
        Admin test = adminRepository.findByUsername(username);
        if(test  != null) {
            session.setAttribute("userId", test.getId());
            session.setAttribute("username", test.getUsername());

            model.addAttribute("routes", routeService.findAll());

            return "routes";
        }


        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout (HttpSession session, Model model) {
        if(session.getAttribute("userId")  != null) {
            session.invalidate();
        }


        return "login";
    }
}
