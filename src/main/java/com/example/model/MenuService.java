package com.example.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.enums.Category;


public class MenuService {


    private final ProductCatalog catalog;
    private final MenuItemFactory beverageFactory;
    private final MenuItemFactory pastryFactory;


    // creates a Menu Service with seperate factories for both beverages snd pastries
    public MenuService(ProductCatalog catalog, MenuItemFactory beverageFactory, MenuItemFactory pastryFactory) {

        if(catalog == null || beverageFactory == null || pastryFactory == null) {
            throw new IllegalArgumentException("Catalog and Factories must not be null");
        }
        this.catalog = catalog;
        this.beverageFactory = beverageFactory;
        this.pastryFactory = pastryFactory;
    }


    // creating and adding a new beverage to the catalog
    public MenuItem addBeverage(String Id, String name, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        
        MenuItem item = beverageFactory.createMenuItem(Id, name, Category.BEVERAGE, basePrice, baseIngredientConsumption);
        catalog.addItem(item);
        return item;
    }


    // creating and adding a new pastry to the catalog
    public MenuItem addPastry(String Id, String name, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        
        MenuItem item = pastryFactory.createMenuItem(Id, name, Category.PASTRY, basePrice, baseIngredientConsumption);
        catalog.addItem(item);
        return item;
    }

    public void addMenuItem(MenuItem item) {
        catalog.addItem(item);
    }

    public void updateMenuItem(MenuItem item) {
        catalog.updateItem(item);
    }

    public void removeItem(String Id) {
        catalog.removeItem(Id);
    }

    public Collection<MenuItem> getAllItems() {
        return catalog.getAllItems();
    }

    public List<MenuItem> getItemsByCategory(Category category) {
        return catalog.getByCategory(category);
    }

    public Optional<MenuItem> findItemById(String Id) {
        return catalog.findById(Id);
    }

}




