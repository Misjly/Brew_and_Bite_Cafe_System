package com.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.enums.Category;

// this is the catalog for all the products that will be available in the cafe
public class ProductCatalog {

    private final Map<String, MenuItem> items = new HashMap<>();

    // this method is adding a new Menu Item to the catalog and if the item Id already exists it is replaced
    public void addItem(MenuItem item) {
        if(item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.put(item.getId(), item);
    }

    // updateing any existing Menu Item and if it doesn't exist yet it is added
    public void updateItem(MenuItem item) {
        addItem(item);
    }

    // Removes a Menu Item from the catalog based on the Id
    public void removeItem(String Id) {
        if (Id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        items.remove(Id);
    }

    // this is going to return a immutable collection of all the menu items in the catalog
    public Collection<MenuItem> getAllItems() {
        return Collections.unmodifiableCollection(items.values());
    }


    public List<MenuItem> getByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items.values()) {
            if (item.getCategory() == category) {
                result.add(item);
            }
        }
        return Collections.unmodifiableList(result);
    }


    public Optional<MenuItem> findById(String Id) {
        if(Id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(items.get(Id));
    }

}