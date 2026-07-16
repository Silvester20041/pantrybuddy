package com.example.pantrybuddy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "http://localhost:5174"
	})@RestController
@RequestMapping("/api/inventory")
public class ImageUploadController {

    @PostMapping("/upload-receipt")
    public ResponseEntity<String> uploadReceipt(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid receipt image");
        }

        // Logic for AI OCR will go here
        return ResponseEntity.ok("Image received. Processing with AI...");
    }
}