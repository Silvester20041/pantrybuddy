package com.example.pantrybuddy.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;
import com.example.pantrybuddy.service.ai.OCRService;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptUploadController {

    @Autowired
    private OCRService ocrService;

    @Autowired
    private ItemRepository itemRepository;
 

    
    @PostMapping("/process")
    public ResponseEntity<?> processReceipt(@RequestParam("file") MultipartFile file) throws IOException {
        String rawText = ocrService.extractTextFromImage(file);
        System.out.println(rawText);
        
        if (rawText.startsWith("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("AI could not read the image.");
        }

        List<FoodItem> itemsFound = ocrService.parseTextToItems(rawText);

        // Filter using Streams as we agreed—this is the Senior Architect way.
        List<FoodItem> validItems = itemsFound.stream()
            .filter(item -> item.getExpiryDate().isAfter(LocalDate.now()))
            .collect(Collectors.toList());

        if (validItems.isEmpty()) {
            return ResponseEntity.ok("No valid future-dated items were found on this receipt.");
        }

        itemRepository.saveAll(validItems);
        return ResponseEntity.ok(validItems);
    }
}
