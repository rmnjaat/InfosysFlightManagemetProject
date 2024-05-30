package com.infosysSpringboard.flightManagementSystem.dao;

import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import com.infosysSpringboard.flightManagementSystem.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    List<Schedule> findAllBySourceAndDestination(Airport source, Airport destination);

}
