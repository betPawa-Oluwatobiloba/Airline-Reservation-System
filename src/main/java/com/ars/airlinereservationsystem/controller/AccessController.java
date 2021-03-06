package com.ars.airlinereservationsystem.controller;

import com.ars.airlinereservationsystem.models.*;
import com.ars.airlinereservationsystem.service.AdminServices;
import com.ars.airlinereservationsystem.service.FlightServices;
import com.ars.airlinereservationsystem.service.PassengerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccessController {


    private final PassengerServices passengerServices;
    private final AdminServices adminServices;
    private final FlightServices flightServices;

    @Autowired
    public AccessController(PassengerServices passengerServices, AdminServices adminServices,FlightServices flightServices) {
        this.passengerServices = passengerServices;
        this.adminServices = adminServices;
        this.flightServices = flightServices;
    }

    @GetMapping("/")
    public String landingPage(Model model,HttpSession session){
        //model.addAttribute("airlineData",new Airline());
        model.addAttribute("flightList",session.getAttribute("flightList"));
        model.addAttribute("passengerData", new Passenger());
        model.addAttribute("flightData", new Flight());
        model.addAttribute("flightBookingData", new Flight());
        model.addAttribute("flightSearchData", new SearchBean());
        System.out.println(flightServices.getAllFlights());
        model.addAttribute("listOfFlightsCreated",flightServices.getAllFlights());
        return "index";
    }



    @GetMapping("/Admin")
    public String adminLogin(Model model){
        model.addAttribute("adminData", new Admin());
        return "admin_login";
    }

    @PostMapping("/passengerLogin")
    public String userLogin(@ModelAttribute ("passengerData") Passenger passenger, HttpSession session, Model model){
        return passengerServices.login(passenger,session,model);
    }

    @PostMapping("/adminLogin")
    public String userLogin(@ModelAttribute("adminData") Admin admin, HttpSession session,Model model){
        return adminServices.login(admin,session,model);
    }

    @RequestMapping("/registration")
    public String passengerRegistration(@ModelAttribute("passengerData") Passenger passenger,Model model){
        return passengerServices.register(passenger,model);
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("adminData");
        httpSession.removeAttribute("passengerData");
        httpSession.invalidate();
        return "redirect:/";
    }

}
