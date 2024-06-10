package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import com.infosysSpringboard.flightManagementSystem.entity.Schedule;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledFlightService {
    List<ScheduledFlight> scheduleFlight(ScheduledFlight scheduledFlight);
    List<ScheduledFlight> viewScheduledFlights();
    List<ScheduledFlight> viewScheduledFlights(String sourceCode, String destinationCode, LocalDate date);
    List<ScheduledFlight> viewScheduledFlights(Integer flightNumber);
    ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule,Integer seatCapacity, Double cost);
    List<ScheduledFlight> deleteScheduledFlight(Integer id);
    ScheduledFlight viewScheduledFlights(Integer id,Integer flightNumber);
    boolean validateScheduleFlight(ScheduledFlight scheduledFlight);


}
