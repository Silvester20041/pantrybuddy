package com.example.pantrybuddy.service.ai;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pantrybuddy.model.FoodItem;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OCRService {

    public String extractTextFromImage(MultipartFile file) throws IOException {
        try {
            // Convert MultipartFile to a temporary File object
            File convFile = convertMultiPartToFile(file);
            
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); 
            tesseract.setLanguage("eng");// Ensure this path exists!
            System.out.println("File Created: " + convFile.getAbsolutePath());
            System.out.println("File Exists: " + convFile.exists());
            return tesseract.doOCR(convFile);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR_UNREADABLE";
        }
    }
    private File convertMultiPartToFile(MultipartFile file)
            throws IOException {

        File convFile = File.createTempFile("receipt", ".jpg");

        file.transferTo(convFile);

        return convFile;
    }
    public String categorize(String itemName) {

        String lower = itemName.toLowerCase();

        if (lower.contains("milk")
                || lower.contains("cheese")
                || lower.contains("yogurt")
                || lower.contains("butter")) {
            return "Dairy";
        }

        if (lower.contains("apple")
                || lower.contains("banana")
                || lower.contains("orange")) {
            return "Fruit";
        }

        if (lower.contains("tomato")
                || lower.contains("spinach")
                || lower.contains("cucumber")
                || lower.contains("cabbage")
                || lower.contains("carrot")
                || lower.contains("onion")) {
            return "Vegetable";
        }

        if (lower.contains("chicken")
                || lower.contains("beef")
                || lower.contains("fish")
                || lower.contains("egg")) {
            return "Protein";
        }

        if (lower.contains("rice")
                || lower.contains("bread")
                || lower.contains("cereal")) {
            return "Grains";
        }

        return "General";
    }
	public List<FoodItem> parseTextToItems(String rawText) {

	    List<FoodItem> items = new ArrayList<>();

	    String[] lines = rawText.split("\\r?\\n");

	    for(String line : lines) {

	        line = line.trim();
	        
	        String lower = line.toLowerCase();

	        if(lower.contains("cash")
	                || lower.contains("receipt")
	                || lower.contains("main st")
	                || lower.contains("street")
	                || lower.contains("city")
	                || lower.contains("phone")
	                || lower.contains("manager")
	                || lower.contains("cashier")
	                || lower.contains("date")
	                || lower.contains("total")) {
	            continue;
	        }

	        if(line.isEmpty()) {
	            continue;
	        }
	        String lower1 = line.toLowerCase();

	        // Ignore receipt metadata
	        if (lower1.contains("mall")
	                || lower1.contains("store")
	                || lower1.contains("address")
	                || lower1.contains("phone")
	                || lower1.contains("cashier")
	                || lower1.contains("manager")
	                || lower1.contains("date")
	                || lower1.contains("total")
	                || lower1.contains("gst")
	                || lower1.contains("tax")
	                || lower1.contains("thank")) {
	            continue;
	        }
	        String lowerFood = line.toLowerCase();
	        if(!(lowerFood.contains("milk")
	                || lowerFood.contains("yogurt")
	                || lowerFood.contains("apple")
	                || lowerFood.contains("banana")
	                || lowerFood.contains("tomato")
	                || lowerFood.contains("spinach")
	                || lowerFood.contains("cucumber")
	                || lowerFood.contains("cabbage")
	                || lowerFood.contains("rice")
	                || lowerFood.contains("bread")
	                || lowerFood.contains("cereal")
	                || lowerFood.contains("egg")
	                || lowerFood.contains("chicken"))) {

	            continue;
	        }
	        String[] words = line.split(" ");

	        StringBuilder cleaned = new StringBuilder();

	        for(String word : words) {

	            if(word.length() <= 1)
	                continue;

	            cleaned.append(word).append(" ");
	        }

	        line = cleaned.toString().trim();

	        // Remove prices and special characters
	        line = line.replaceAll("[0-9.]+", "");
	        line = line.replaceAll("[^a-zA-Z ]", "");
	        line = line.replaceAll("\\b[aA]\\b", "").trim();
	        line = line.replaceAll("\\s+", " ");
	        line = line.trim();

	        // Ignore very short garbage text
	        if (line.length() < 3) {
	            continue;
	        }
	        if(!line.matches(".*[a-zA-Z]{3,}.*")) {
	            continue;
	        }

	        FoodItem item = new FoodItem();

	        item.setName(line);

	        item.setCategory(categorize(line));

	        item.setQuantity(1);

	        item.setExpiryDate(
	                LocalDate.now().plusDays(7));

	        items.add(item);
	    }

	    return items;
	}
}