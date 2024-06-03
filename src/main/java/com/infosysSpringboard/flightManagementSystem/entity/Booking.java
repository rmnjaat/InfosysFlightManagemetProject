package com.infosysSpringboard.flightManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passengers;
    private Double cost;
    @OneToOne
    private Flight flight;
    private Integer noOfPassengers;

}
