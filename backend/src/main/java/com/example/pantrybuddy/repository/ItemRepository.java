
package com.example.pantrybuddy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pantrybuddy.model.FoodItem;

@Repository

public interface ItemRepository extends JpaRepository<FoodItem, Long> {
//	itemRepository.saveAll(listOfFoodItems);
    List<FoodItem> findByExpiryDate(LocalDate date);
    List<FoodItem> findByExpiryDateBefore(LocalDate date);
    List<FoodItem> findByQuantityLessThanEqual(int quantity);
}