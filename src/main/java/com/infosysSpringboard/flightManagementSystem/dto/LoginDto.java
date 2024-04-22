package com.infosysSpringboard.flightManagementSystem.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String userNameOrEmail;
    private String password;
}
