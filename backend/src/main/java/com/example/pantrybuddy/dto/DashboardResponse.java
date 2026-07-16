package com.example.pantrybuddy.dto;

import lombok.Data;

@Data
public class DashboardResponse {

    private long totalItems;
    private long dairyItems;
    private long vegetableItems;
    private long grainItems;
    private long fruitItems;
    private long proteinItems;
    private long expiringSoon;
}