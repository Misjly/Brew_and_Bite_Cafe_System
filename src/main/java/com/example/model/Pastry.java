package com.example.model;

import java.math.BigDecimal;
import java.util.Map;

import com.example.enums.Category;

public class Pastry extends MenuItem {

    private String variant;

    public Pastry(String Id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        super(Id, name, category, basePrice, baseIngredientConsumption);
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
