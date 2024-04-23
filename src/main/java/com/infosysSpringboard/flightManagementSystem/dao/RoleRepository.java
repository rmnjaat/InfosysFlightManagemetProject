package com.infosysSpringboard.flightManagementSystem.dao;

import com.infosysSpringboard.flightManagementSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
