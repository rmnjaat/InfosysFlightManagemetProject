package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import com.infosysSpringboard.flightManagementSystem.entity.Schedule;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;
import com.infosysSpringboard.flightManagementSystem.service.ScheduledFlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleFlightController {

    private ScheduledFlightService scheduledFlightService;

    public ScheduleFlightController(ScheduledFlightService scheduledFlightService) {
        this.scheduledFlightService = scheduledFlightService;
    }

    @GetMapping("/all")
    public List<ScheduledFlight> getScheduledFlights(){
        return scheduledFlightService.viewScheduledFlights();
    }
    @PostMapping("/allBetweenAirports")
    public List<ScheduledFlight> getScheduledFlightsFromAirport(@RequestBody Map<String, Object> requestBody) {
        String source = (String) requestBody.get("source");
        String destination = (String) requestBody.get("destination");
        LocalDate date = LocalDate.parse((String)requestBody.get("date"));
        return scheduledFlightService.viewScheduledFlights(source,destination,date);
    }

    @GetMapping("/all/{id}")
    public List<ScheduledFlight> getScheduledFlightFromFlight(@PathVariable Integer id){
        return scheduledFlightService.viewScheduledFlights(id);
    }

    @PostMapping("/add")
    public List<ScheduledFlight> scheduleFlight(@RequestBody ScheduledFlight scheduledFlight){
        return scheduledFlightService.scheduleFlight(scheduledFlight);
    }

    @PutMapping("/modify")
    public ScheduledFlight modifyScheduledFlight(@RequestBody ScheduledFlight modifiedSchedule){
        Flight flight = modifiedSchedule.getFlight();
        Schedule schedule = modifiedSchedule.getSchedule();
        Integer seatCapacity = modifiedSchedule.getAvailableSeats();
        return scheduledFlightService.modifyScheduledFlight(flight,schedule,seatCapacity);
    }

    @DeleteMapping("/{id}")
    public List<ScheduledFlight> deleteScheduledFlight(@PathVariable Integer id){
        return scheduledFlightService.deleteScheduledFlight(id);
    }

}
