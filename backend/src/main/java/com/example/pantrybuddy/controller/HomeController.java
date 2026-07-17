package com.example.pantrybuddy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "{\"app\":\"Pantry Buddy API\",\"status\":\"running\",\"docs\":\"/api/dashboard\"}";
    }
}
