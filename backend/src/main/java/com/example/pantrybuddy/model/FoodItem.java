package com.example.pantrybuddy.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity // This is from your notes! It marks this as a DB table.
@Table(name = "items")
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Integer quantity;
    private LocalDate expiryDate;
    private boolean notified = false;

    // IMPORTANT: Eclipse can write your Getters/Setters for you!
    // Right-click anywhere here -> Source -> Generate Getters and Setters... -> Select All -> Generate.
}