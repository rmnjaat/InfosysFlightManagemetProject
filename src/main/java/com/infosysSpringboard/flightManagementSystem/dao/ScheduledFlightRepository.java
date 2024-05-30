package com.infosysSpringboard.flightManagementSystem.dao;

import com.infosysSpringboard.flightManagementSystem.entity.Flight;
import com.infosysSpringboard.flightManagementSystem.entity.Schedule;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight,Integer> {
    List<ScheduledFlight> findAllBySchedule(Schedule schedule);
    List<ScheduledFlight> findAllByFlight(Flight flight);
    ScheduledFlight findByScheduleAndFlight(Schedule schedule, Flight flight);
    ScheduledFlight findByFlight(Flight flight);
}
