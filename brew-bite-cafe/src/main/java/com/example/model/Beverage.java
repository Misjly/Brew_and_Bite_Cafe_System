package com.example.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.enums.BeverageSize;
import com.example.enums.Category;

public class Beverage extends MenuItem {

    private List<CustomizationOption> customizationOptions;
    private BeverageSize size;

    public Beverage(String Id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        super(Id, name, category, basePrice, baseIngredientConsumption);
    }

    public void addCustomizationOption(CustomizationOption option) {
        if (option == null) {
            throw new IllegalArgumentException("Customization option cannot be null");
        }
        this.customizationOptions.add(option);
    }

    public List<CustomizationOption> getCustomizationOptions() {
        return customizationOptions;
    }

    public BeverageSize getSize() {
        return size;
    }

    public void setSize(BeverageSize size) {
        this.size = size;
    }
}
