package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.entity.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> addFlight(Flight flight);
    List<Flight> modifyFlight(Flight flight);
    Flight findFlight(Integer number);
    List<Flight> viewFlights();
    List<Flight> deleteFlight(Integer number);
    boolean validateFlight(Flight flight);
}
