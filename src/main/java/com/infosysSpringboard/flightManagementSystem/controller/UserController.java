package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.dao.RoleRepository;
import com.infosysSpringboard.flightManagementSystem.entity.User;
import com.infosysSpringboard.flightManagementSystem.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController

public class UserController {

    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> viewUsers(){
        return userService.findAll();
    }

    @PostMapping("/users")
    public List<User> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName(user.getUserType())));
        userService.addUser(user);
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User viewUser(@PathVariable int id){
        if(userService.findUserById(id)==null){
            throw new RuntimeException("User with ID-"+id+" not found");
        }
        return userService.findUserById(id);
    }

    @PutMapping("/users")
    public List<User> updateUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName(user.getUserType())));
        userService.updateUser(user);
        return userService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id){
        if(userService.findUserById(id)==null){
            throw new RuntimeException("User with ID-"+id+" not found");
        }
        userService.deleteUser(id);
        return userService.findAll();
    }


}
