package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import com.infosysSpringboard.flightManagementSystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public List<Airport> viewAirports(){
        return airportService.airports();
    }

    @GetMapping("/airports/{code}")
    public Airport viewAirport(@PathVariable String code){
        return airportService.airport(code);
    }
}
