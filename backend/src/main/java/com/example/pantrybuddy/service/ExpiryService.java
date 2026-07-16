package com.example.pantrybuddy.service;

import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class ExpiryService {

    @Autowired
    private ItemRepository itemRepository;

    // This runs every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpirations() {
        LocalDate twoDaysFromNow = LocalDate.now().plusDays(2);
        List<FoodItem> expiringSoon = itemRepository.findByExpiryDate(twoDaysFromNow); // 
        
        for (FoodItem item : expiringSoon) {
            sendNotification(item);
        }
    }

    private void sendNotification(FoodItem item) {
        System.out.println("ALERT: " + item.getName() + " expires in 2 days! Check your recipes.");
        // Future: Integration with Email or AI Recipe Engine here.
    }
}