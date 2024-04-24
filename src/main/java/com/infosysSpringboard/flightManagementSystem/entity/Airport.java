package com.infosysSpringboard.flightManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @Column(unique = true)
    private String code;
    private String name;
    private String location;
}
