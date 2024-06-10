package com.infosysSpringboard.flightManagementSystem.service;

public interface EmailService {
    void sendMail(String to, String subject, String body);
}
