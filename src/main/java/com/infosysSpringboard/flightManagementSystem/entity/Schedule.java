package com.infosysSpringboard.flightManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Airport source;

    @OneToOne
    private Airport destination;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

}
