package com.infosysSpringboard.flightManagementSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements  EmailService{

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public void sendMail(String to, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
