package com.example.model;

import java.math.BigDecimal;
import java.util.Map;

import com.example.enums.Category;


public abstract class MenuItemFactory {

    public abstract MenuItem createMenuItem(String id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption);

}