package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import com.infosysSpringboard.flightManagementSystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<Flight> findFlights(){
        return flightService.viewFlights();
    }

    @GetMapping("/flights/{number}")
    public Flight findFlight(@PathVariable int number){
        return flightService.findFlight(number);
    }

    @PostMapping("/flights")
    public List<Flight> addFlight(@RequestBody Flight flight){
        if(flightService.validateFlight(flight)){
            throw new RuntimeException("Flight with similar attributes already exist");
        }
        flightService.addFlight(flight);
        return  flightService.viewFlights();
    }

    @PutMapping("/flights")
    public List<Flight> modifyFlight(@RequestBody Flight flight){

        flightService.addFlight(flight);
        return  flightService.viewFlights();
    }

    @DeleteMapping("/flights/{number}")
    public List<Flight> deleteFlight(@PathVariable Integer number){
        flightService.deleteFlight(number);
        return flightService.viewFlights();
    }



}
