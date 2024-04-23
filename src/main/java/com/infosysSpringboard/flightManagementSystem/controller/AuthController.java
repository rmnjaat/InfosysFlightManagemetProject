package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.dao.RoleRepository;
import com.infosysSpringboard.flightManagementSystem.dao.UserRepository;
import com.infosysSpringboard.flightManagementSystem.dto.LoginDto;
import com.infosysSpringboard.flightManagementSystem.dto.RegisterDto;
import com.infosysSpringboard.flightManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserNameOrEmail(), loginDto.getPassword()));



        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User loged-in successfully !", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody RegisterDto registerDto) {

        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setUserType(registerDto.getUserType());
        user.setPhone(registerDto.getPhone());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setRoles(Arrays.asList(roleRepository.findByName(user.getUserType())));
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfull", HttpStatus.OK);
    }
}
