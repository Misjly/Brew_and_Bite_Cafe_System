import java.util.Map;
import java.util.Objects;

/**
 * InventoryService
 *
 * Service layer that manages inventory operations and business logic.
 * Handles adding stock, consuming ingredients, and checking fulfillment.
 * 
 * @author Kofi
 */
public class InventoryService {
    
    private final Inventory inventory;
    
    /**
     * Creates a new inventory service.
     *
     * @param inventory the inventory to manage
     * @throws NullPointerException if inventory is null
     */
    public InventoryService(Inventory inventory) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        this.inventory = inventory;
    }
    
    /**
     * Adds stock for an ingredient. If the ingredient already exists,
     * increases its quantity; otherwise, creates a new stock entry.
     *
     * @param ingredient the ingredient to add stock for
     * @param amount quantity to add (must be non-negative)
     * @throws NullPointerException if ingredient is null
     * @throws IllegalArgumentException if amount is negative or ingredient name is invalid
     */
    public void addStock(Ingredient ingredient, double amount) {
        Objects.requireNonNull(ingredient, "Ingredient cannot be null");
        
        String key = ingredient.getName();
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        
        IngredientStock stock = inventory.getStock(key);
        
        if (stock == null) {
            // Create new stock entry
            stock = new IngredientStock(ingredient, amount);
            inventory.putStock(key, stock);
        } else {
            // Increase existing quantity
            stock.increaseQuantity(amount);
        }
    }
    
    /**
     * Retrieves the stock record for the specified ingredient.
     *
     * @param ingredientName name of the ingredient to look up
     * @return the IngredientStock object, or null if not found
     * @throws IllegalArgumentException if ingredient name is null or empty
     */
    public IngredientStock getStock(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        
        return inventory.getStock(ingredientName);
    }
    
    /**
     * Returns the available quantity for the given ingredient.
     * Returns 0.0 if the ingredient is not in inventory.
     *
     * @param ingredientName name of the ingredient to query
     * @return available quantity, or 0.0 if ingredient not found
     * @throws IllegalArgumentException if ingredient name is null or empty
     */
    public double getAvailableQuantity(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        
        IngredientStock stock = inventory.getStock(ingredientName);
        return (stock == null) ? 0.0 : stock.getQuantityAvailable();
    }
    
    /**
     * Removes an ingredient entirely from the inventory.
     *
     * @param ingredientName name of the ingredient to remove
     * @throws IllegalArgumentException if ingredient name is null or empty
     */
    public void remove(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        
        inventory.removeStock(ingredientName);
    }
    
    /**
     * Returns all stocks in the inventory.
     *
     * @return map of all ingredient stocks
     */
    public Map<String, IngredientStock> getAllStocks() {
        return inventory.getAllStocks();
    }
    
    /**
     * Checks if a specific ingredient is currently in stock.
     * 
     * @param ingredientName name of the ingredient to check
     * @return true if ingredient exists in inventory, false otherwise
     * @throws IllegalArgumentException if ingredient name is null or empty
     */
    public boolean hasIngredient(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        
        return inventory.hasIngredient(ingredientName);
    }
    
    /**
     * Consumes ingredients from inventory for a given recipe/order.
     * Validates all inputs before consuming. Returns false if any
     * ingredient is invalid, not found, or has insufficient stock.
     *
     * @param ingredientAmounts map of ingredient names to amounts needed
     * @return true if ingredients were successfully consumed, false otherwise
     */
    public boolean consumeFor(Map<String, Double> ingredientAmounts) {
        if (ingredientAmounts == null || ingredientAmounts.isEmpty()) {
            return false;
        }
        
        try {
            // Validate all ingredients and amounts
            for (Map.Entry<String, Double> entry : ingredientAmounts.entrySet()) {
                String ingredientName = entry.getKey();
                Double amount = entry.getValue();
                
                if (ingredientName == null || ingredientName.trim().isEmpty()) {
                    return false;
                }
                
                if (amount == null || amount < 0) {
                    return false;
                }
                
                IngredientStock stock = inventory.getStock(ingredientName);
                if (stock == null) {
                    return false;
                }
                
                if (stock.getQuantityAvailable() < amount) {
                    return false;
                }
            }
            
            // Consume the ingredients
            for (Map.Entry<String, Double> entry : ingredientAmounts.entrySet()) {
                String ingredientName = entry.getKey();
                Double amount = entry.getValue();
                IngredientStock stock = inventory.getStock(ingredientName);
                stock.decreaseQuantity(amount);
            }
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Checks if the inventory can fulfill an order.
     * Does not modify inventory.
     *
     * @param order the order to check
     * @return true if all ingredients are available in sufficient quantities, false otherwise
     */
    public boolean canFulfill(Order order) {
        if (order == null) {
            return false;
        }
        
        try {
            Map<String, Double> requiredIngredients = order.getRequiredIngredients();
            
            if (requiredIngredients == null || requiredIngredients.isEmpty()) {
                return true;
            }
            
            // Check each required ingredient
            for (Map.Entry<String, Double> entry : requiredIngredients.entrySet()) {
                String ingredientName = entry.getKey();
                Double requiredAmount = entry.getValue();
                
                if (ingredientName == null || requiredAmount == null || requiredAmount <= 0) {
                    continue;
                }
                
                IngredientStock stock = inventory.getStock(ingredientName);
                
                if (stock == null || stock.getQuantityAvailable() < requiredAmount) {
                    return false;
                }
            }
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets the underlying inventory instance.
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
}
