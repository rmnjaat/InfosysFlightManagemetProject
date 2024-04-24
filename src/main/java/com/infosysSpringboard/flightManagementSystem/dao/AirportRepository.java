package com.infosysSpringboard.flightManagementSystem.dao;

import com.infosysSpringboard.flightManagementSystem.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,String> {
}
