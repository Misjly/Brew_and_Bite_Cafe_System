package com.example.model;

import java.math.BigDecimal;
import java.util.Map;

import com.example.enums.Category;

public class BeverageFactory extends MenuItemFactory {

    @Override
    public MenuItem createMenuItem(String id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        return new Beverage(id, name, category, basePrice, baseIngredientConsumption);
    }
    
}
