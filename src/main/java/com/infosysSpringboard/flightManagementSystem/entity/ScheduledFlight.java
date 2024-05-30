package com.infosysSpringboard.flightManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "scheduledFlights")
public class ScheduledFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Flight flight;

    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    private Integer availableSeats;
}
