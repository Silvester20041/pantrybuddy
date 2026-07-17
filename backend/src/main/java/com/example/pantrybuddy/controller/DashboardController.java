package com.example.pantrybuddy.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pantrybuddy.dto.DashboardResponse;
import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class DashboardController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/api/dashboard")
    public DashboardResponse getDashboard() {

        List<FoodItem> items = repository.findAll();

        DashboardResponse response = new DashboardResponse();

        response.setTotalItems(items.size());

        response.setDairyItems(
                items.stream()
                        .filter(i -> "Dairy".equalsIgnoreCase(i.getCategory()))
                        .count());

        response.setVegetableItems(
                items.stream()
                        .filter(i -> "Vegetable".equalsIgnoreCase(i.getCategory()))
                        .count());

        response.setGrainItems(
                items.stream()
                        .filter(i -> "Grains".equalsIgnoreCase(i.getCategory()))
                        .count());

        response.setFruitItems(
                items.stream()
                        .filter(i -> "Fruit".equalsIgnoreCase(i.getCategory()))
                        .count());

        response.setProteinItems(
                items.stream()
                        .filter(i -> "Protein".equalsIgnoreCase(i.getCategory()))
                        .count());

        response.setExpiringSoon(
                items.stream()
                        .filter(i ->
                                i.getExpiryDate() != null &&
                                i.getExpiryDate().isBefore(LocalDate.now().plusDays(3)))
                        .count());

        return response;
    }
}