package com.example.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.enums.Category;

public abstract class MenuItem {

// this is going to be the base class for all the menu items in the Brew & Bite Cafe
    private final String Id;
    private final String name;
    private final Category category;
    private final BigDecimal basePrice;

    private final Map<String, Double> baseIngredientConsumption;

    public MenuItem(String Id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {

        if (Id == null || Id.isBlank()) {
            throw new IllegalArgumentException("Id cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (basePrice == null || basePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Base price cannot be null or negative");
        }

        this.Id = Id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;

        if (baseIngredientConsumption == null) {
            this.baseIngredientConsumption = Collections.emptyMap();
        } else {
            this.baseIngredientConsumption = new HashMap<>(baseIngredientConsumption);
        }
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public Map<String, Double> getBaseIngredientConsumption() {
        return baseIngredientConsumption;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "Id ='" + Id + '\'' + " name = '" + name + '\'' + " category =" + category + " basePrice =" + basePrice + " baseIngredientConsumption =" + baseIngredientConsumption + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) object;
        return Objects.equals(Id, menuItem.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

}
