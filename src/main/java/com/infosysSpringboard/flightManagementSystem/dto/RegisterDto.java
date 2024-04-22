package com.infosysSpringboard.flightManagementSystem.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String userType;
    private String userName;
    private String password;
    private String phone;
    private String email;
}
