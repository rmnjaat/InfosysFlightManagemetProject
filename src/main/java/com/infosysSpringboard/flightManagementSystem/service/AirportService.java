package com.infosysSpringboard.flightManagementSystem.service;


import com.infosysSpringboard.flightManagementSystem.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> airports();
    Airport airport(String code);
}

