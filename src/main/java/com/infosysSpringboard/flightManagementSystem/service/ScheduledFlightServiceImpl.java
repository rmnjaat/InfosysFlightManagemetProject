package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.dao.AirportRepository;
import com.infosysSpringboard.flightManagementSystem.dao.FlightRepository;
import com.infosysSpringboard.flightManagementSystem.dao.ScheduleRepository;
import com.infosysSpringboard.flightManagementSystem.dao.ScheduledFlightRepository;
import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import com.infosysSpringboard.flightManagementSystem.entity.Schedule;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ScheduledFlightServiceImpl implements ScheduledFlightService{

    private ScheduledFlightRepository scheduledFlightRepository;
    private FlightRepository flightRepository;
    private AirportRepository airportRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduledFlightServiceImpl(ScheduledFlightRepository scheduledFlightRepository, FlightRepository flightRepository, AirportRepository airportRepository, ScheduleRepository scheduleRepository) {
        this.scheduledFlightRepository = scheduledFlightRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduledFlight> scheduleFlight(ScheduledFlight scheduledFlight) {
        if(validateScheduleFlight(scheduledFlight)){
            scheduledFlightRepository.save(scheduledFlight);
        }
        return scheduledFlightRepository.findAll();
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlights() {
        return scheduledFlightRepository.findAll();
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlights(String sourceCode, String destinationCode, LocalDate date) {
        Airport source = airportRepository.findById(sourceCode).get();
        Airport destination = airportRepository.findById(destinationCode).get();
        List<Schedule> schedules = scheduleRepository.findAllBySourceAndDestination(source,destination);
        System.out.println(schedules);
        Schedule schedule = null;
        for(Schedule s:schedules){
            if(s.getArrivalTime().toLocalDate().equals(date) || s.getDepartureTime().toLocalDate().equals(date)){
                schedule=s;
                break;
            }
        }
        return scheduledFlightRepository.findAllBySchedule(schedule);
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlights(Integer flightNumber) {
        Optional<Flight> flight = flightRepository.findById(flightNumber);
        return scheduledFlightRepository.findAllByFlight(flight.get());
    }

    @Override
    public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, Integer seatCapacity) {
        ScheduledFlight scheduledFlight = scheduledFlightRepository.findByFlight(flight);
        scheduledFlight.setFlight(flight);
        scheduledFlight.setSchedule(schedule);
        scheduledFlight.setAvailableSeats(seatCapacity);
        scheduledFlightRepository.save(scheduledFlight);
        return scheduledFlight;
    }

    @Override
    public List<ScheduledFlight> deleteScheduledFlight(Integer id) {
        scheduledFlightRepository.deleteById(id);
        return scheduledFlightRepository.findAll();
    }

    @Override
    public ScheduledFlight viewScheduledFlights(Integer id, Integer flightNumber) {
        Optional<Flight> flight = flightRepository.findById(flightNumber);
        return scheduledFlightRepository.findByIdAndFlight(id,flight.get());
    }

    @Override
    public boolean validateScheduleFlight(ScheduledFlight scheduledFlight) {
        Optional<Flight> flight  = flightRepository.findById(scheduledFlight.getFlight().getFlightNumber());
        Airport source = scheduledFlight.getSchedule().getSource();
        Airport destination = scheduledFlight.getSchedule().getDestination();
        Optional<Airport> tSource = airportRepository.findById(source.getCode());
        Optional<Airport> tDestination = airportRepository.findById(destination.getCode());
        if(flight.isEmpty() ||
                !Objects.equals(flight.get().getFlightModel(), scheduledFlight.getFlight().getFlightModel()) ||
                !Objects.equals(flight.get().getCarrierName(), scheduledFlight.getFlight().getCarrierName()) ||
                !Objects.equals(flight.get().getSeatCapacity(), scheduledFlight.getFlight().getSeatCapacity())
        ){

            throw new RuntimeException("Flight details are invalid");
        }
        if (tSource.isEmpty() || tDestination.isEmpty() ||
                Objects.equals(source,destination) ||
                !Objects.equals(source.getLocation(),tSource.get().getLocation()) ||
                !Objects.equals(source.getName(),tSource.get().getName()) ||
                !Objects.equals(destination.getLocation(),tDestination.get().getLocation()) ||
                !Objects.equals(destination.getName(),tDestination.get().getName())
        ){
            throw new RuntimeException("Flight details are invalid");
        }
        if(scheduledFlight.getAvailableSeats()>flight.get().getSeatCapacity()){
            throw new RuntimeException("Available seats are more that the seat capacity");
        }
        // example date-time format "2023-08-04T10:00:00"
        String departureTimeString = scheduledFlight.getSchedule().getDepartureTime().toString();
        String arrivalTimeString = scheduledFlight.getSchedule().getArrivalTime().toString();

        System.out.println(departureTimeString);
       // Define the expected format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Validate departure time format
        try {
            LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Departure time format is invalid.");
            return false;
        }

        // Validate arrival time format
        try {
            LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Arrival time format is invalid.");
            return false;
        }
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, formatter);
        if (!departureTime.isBefore(arrivalTime)) {
            System.out.println("Invalid Arrival time or Departure time");
            return false;
        }
        return true;
    }
}
