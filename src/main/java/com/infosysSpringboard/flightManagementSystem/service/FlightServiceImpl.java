package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.dao.FlightRepository;
import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> addFlight(Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> modifyFlight(Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findAll();
    }

    @Override
    public Flight findFlight(Integer number) {
        Optional<Flight> result = flightRepository.findById(number);
        Flight flight = null;
        if(result.isPresent()){
            flight = result.get();
        }
        else{
            throw new RuntimeException("Flight with flight number: "+number+" not found");
        }
        return flight;
    }

    @Override
    public List<Flight> viewFlights() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> deleteFlight(Integer number) {
        Optional<Flight> result = flightRepository.findById(number);
        Flight flight = null;
        if(result.isPresent()){
            flight = result.get();
        }
        else{
            throw new RuntimeException("Flight with flight number: "+number+" not found");
        }
        flightRepository.delete(flight);
        return flightRepository.findAll();
    }
}
