package com.example.model;
import java.util.Objects;

public class IngredientStock {
    
    private final Ingredient ingredient;
    private double quantityAvailable;
    
    /**
     * Creates a new ingredient stock record.
     *
     * @param ingredient the ingredient
     * @param quantityAvailable the initial quantity available
     * @throws NullPointerException if ingredient is null
     * @throws IllegalArgumentException if quantity is negative
     */
    public IngredientStock(Ingredient ingredient, double quantityAvailable) {
        Objects.requireNonNull(ingredient, "Ingredient cannot be null");
        
        if (quantityAvailable < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + quantityAvailable);
        }
        
        this.ingredient = ingredient;
        this.quantityAvailable = quantityAvailable;
    }
    
    /**
     * Gets the ingredient.
     *
     * @return the ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }
    
    /**
     * Gets the available quantity.
     *
     * @return the quantity available
     */
    public double getQuantityAvailable() {
        return quantityAvailable;
    }
    
    /**
     * Increases the quantity by the specified amount.
     *
     * @param amount the amount to increase
     * @throws IllegalArgumentException if amount is negative
     */
    public void increaseQuantity(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        this.quantityAvailable += amount;
    }
    
    /**
     * Decreases the quantity by the specified amount.
     *
     * @param amount the amount to decrease
     * @throws IllegalArgumentException if amount is negative or exceeds available quantity
     */
    public void decreaseQuantity(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        if (amount > quantityAvailable) {
            throw new IllegalArgumentException(
                "Cannot decrease by " + amount + ". Only " + quantityAvailable + " available"
            );
        }
        this.quantityAvailable -= amount;
    }
    
    @Override
    public String toString() {
        return ingredient.getName() + ": " + quantityAvailable + " " + ingredient.getUnit();
    }
}
