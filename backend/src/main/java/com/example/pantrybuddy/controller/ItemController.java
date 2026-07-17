package com.example.pantrybuddy.controller;

import com.example.pantrybuddy.model.FoodItem;
import com.example.pantrybuddy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // Marks this as a web-handler
@RequestMapping("/api/items") // The URL path
public class ItemController {

    @Autowired // Dependency Injection - Spring brings the Repository here
    private ItemRepository repository;

    @GetMapping // Get all veggies
    public List<FoodItem> getAllItems() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getItemById(
            @PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/expiring-soon")
    public List<FoodItem> getExpiringSoonItems() {

        return repository.findAll()
                .stream()
                .filter(item ->
                        item.getExpiryDate() != null &&
                        item.getExpiryDate().isAfter(LocalDate.now()) &&
                        item.getExpiryDate().isBefore(LocalDate.now().plusDays(4))
                )
                .toList();
    }
    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateItem(@PathVariable Long id, @RequestBody FoodItem updatedDetails) {
        return repository.findById(id)
            .map(item -> {
                // Update the fields based on user input
                item.setName(updatedDetails.getName());
                item.setCategory(updatedDetails.getCategory());
                item.setExpiryDate(updatedDetails.getExpiryDate());
                item.setQuantity(updatedDetails.getQuantity());
                item.setQuantity(updatedDetails.getQuantity());
                
                FoodItem savedItem = repository.save(item);
                return ResponseEntity.ok(savedItem);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/low-stock")
    public List<FoodItem> getLowStockItems() {

        return repository.findByQuantityLessThanEqual(2);
    }
    @PatchMapping("/{id}/quantity")
    public ResponseEntity<FoodItem> updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return repository.findById(id)
                .map(item -> {
                    item.setQuantity(quantity);
                    return ResponseEntity.ok(repository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // Add a new veggie
    public FoodItem addItem(@RequestBody FoodItem item) {
        return repository.save(item);
    }
    @DeleteMapping("/{id}") // URL will be /api/items/1
    public String deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
        return "Item with ID " + id + " has been removed from your pantry.";
    }
    
}