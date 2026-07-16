package com.example.pantrybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pantrybuddy.model.User;
import com.example.pantrybuddy.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService service;

    // Register
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return service.register(user);

    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User loggedUser =
                service.login(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }

}