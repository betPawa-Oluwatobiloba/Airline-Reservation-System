package com.ars.airlinereservationsystem.controller;

import com.ars.airlinereservationsystem.models.Airline;
import com.ars.airlinereservationsystem.models.Flight;
import com.ars.airlinereservationsystem.service.AirlineService;
import com.ars.airlinereservationsystem.service.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlightController {
    @GetMapping("/create-flight")
    public String flightCreationPage(Model model){
        model.addAttribute("flightData", new Flight());
        model.addAttribute("listOfFlightsCreated",flightServices.getAllFlights());
        return "create-flight";
    }
    private final FlightServices flightServices;
    private final AirlineService airlineService;
    @Autowired
    public FlightController(FlightServices flightServices,AirlineService airlineService) {
        this.flightServices = flightServices;
        this.airlineService = airlineService;
    }
    @PostMapping("/searchFlight")
    public String searchFlights(@ModelAttribute("flightData")Flight flight, Model model){
        model.addAttribute("flightList",flightServices.searchFlight(flight));
        return "index";
    }
    @PostMapping("/createFlight")
    public String createFlight(@ModelAttribute("flightData") Flight flight,Model model){
        flightServices.createFlight(flight);
        return "admin-dashboard";
    }
    @PostMapping("/addAirline")
    public String createFlight(@ModelAttribute("airlineData") Airline airline,Model model){
        //model.addAttribute("listOfFlightsCreated",flightServices.getAllFlights());
        airlineService.addNewAirline(airline);
        return "/admin-dashboard";
    }
}
