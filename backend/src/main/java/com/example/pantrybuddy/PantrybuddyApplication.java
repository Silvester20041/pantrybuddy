package com.example.pantrybuddy;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class PantrybuddyApplication {

    @Autowired
    private ItemRepository repository; // Inject the 'Librarian'

    public static void main(String[] args) {
        SpringApplication.run(PantrybuddyApplication.class, args);
    }

   
}
