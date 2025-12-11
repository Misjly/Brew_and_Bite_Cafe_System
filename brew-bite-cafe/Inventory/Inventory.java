import java.util.HashMap;
import java.util.Map;

/**
 * Inventory
 *
 * Data structure that holds ingredient stocks.
 * Uses a HashMap for O(1) lookup performance.
 * 
 * @author Kofi
 */
public class Inventory {
    
    /**
     * Map storing ingredient stocks.
     * Key: ingredient name
     * Value: corresponding IngredientStock object
     */
    private final Map<String, IngredientStock> stocks;
    
    /**
     * Creates a new empty inventory.
     */
    public Inventory() {
        this.stocks = new HashMap<>();
    }
    
    /**
     * Adds or updates an ingredient stock.
     *
     * @param ingredientName the name of the ingredient
     * @param stock the ingredient stock
     * @throws IllegalArgumentException if ingredient name is null or empty
     * @throws NullPointerException if stock is null
     */
    public void putStock(String ingredientName, IngredientStock stock) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        if (stock == null) {
            throw new NullPointerException("Stock cannot be null");
        }
        stocks.put(ingredientName, stock);
    }
    
    /**
     * Retrieves the stock for an ingredient.
     *
     * @param ingredientName the name of the ingredient
     * @return the IngredientStock, or null if not found
     */
    public IngredientStock getStock(String ingredientName) {
        return stocks.get(ingredientName);
    }
    
    /**
     * Checks if an ingredient exists in the inventory.
     *
     * @param ingredientName the name of the ingredient
     * @return true if ingredient exists, false otherwise
     */
    public boolean hasIngredient(String ingredientName) {
        return stocks.containsKey(ingredientName);
    }
    
    /**
     * Removes an ingredient from the inventory.
     *
     * @param ingredientName the name of the ingredient to remove
     * @return the removed IngredientStock, or null if not found
     */
    public IngredientStock removeStock(String ingredientName) {
        return stocks.remove(ingredientName);
    }
    
    /**
     * Returns all stocks in the inventory.
     *
     * @return map of all ingredient stocks
     */
    public Map<String, IngredientStock> getAllStocks() {
        return stocks;
    }
    
    /**
     * Returns the number of different ingredients in inventory.
     *
     * @return the size of the inventory
     */
    public int size() {
        return stocks.size();
    }
    
    /**
     * Checks if the inventory is empty.
     *
     * @return true if inventory has no ingredients, false otherwise
     */
    public boolean isEmpty() {
        return stocks.isEmpty();
    }
}
