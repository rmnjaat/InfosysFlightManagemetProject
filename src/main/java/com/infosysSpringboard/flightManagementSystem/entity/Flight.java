package com.infosysSpringboard.flightManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightNumber;
    private String flightModel;
    private String carrierName;
    private Integer seatCapacity;

}
