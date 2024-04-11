package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.entity.User;
import com.infosysSpringboard.flightManagementSystem.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> viewUsers(){
        return userService.findAll();
    }

    @PostMapping("/users")
    public List<User> addUser(@RequestBody User user){
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
