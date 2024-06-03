package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.entity.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> findAll();
    public User findUserById(int id);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User getCurrentUser();
}
