package com.example.pantrybuddy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;


@RestController
public class RecipeController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/api/recipes/suggestions")
    public List<String> getRecipeSuggestions() {

        List<FoodItem> items = repository.findAll();

        List<String> recipes = new ArrayList<>();

        boolean hasTomato = items.stream()
                .anyMatch(i -> i.getName().toLowerCase().contains("tomato"));

        boolean hasCucumber = items.stream()
                .anyMatch(i -> i.getName().toLowerCase().contains("cucumber"));

        boolean hasYogurt = items.stream()
                .anyMatch(i -> i.getName().toLowerCase().contains("yogurt"));

        boolean hasCabbage = items.stream()
                .anyMatch(i -> i.getName().toLowerCase().contains("cabbage"));

        if (hasTomato && hasCucumber) {
            recipes.add("Vegetable Salad - Uses Tomatoes and Cucumber");
           
        }

        if (hasYogurt && hasCucumber) {
        	 recipes.add("Raita - Uses Yogurt and Cucumber");
             
            
        }

        if (hasCabbage && hasTomato) {
        	recipes.add("Veggie Bowl - Uses Cabbage and Tomatoes");
        }

        if (recipes.isEmpty()) {
            recipes.add("No recipe suggestions available");
        }

        return recipes;
    }
}