package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.dao.AirportRepository;
import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService{


    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> airports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport airport(String code) {
        Optional<Airport> result=airportRepository.findById(code);
        Airport airport = null;
        if(result.isPresent()){
            airport=result.get();
        }
        else{
            throw new RuntimeException("Airport with code: "+code+" not found");
        }
        return airport;
    }
}
