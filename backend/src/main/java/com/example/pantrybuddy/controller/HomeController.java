package com.example.pantrybuddy.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "app", "Pantry Buddy API",
            "status": "running",
            "docs", "/api/dashboard"
        );
    }
}
