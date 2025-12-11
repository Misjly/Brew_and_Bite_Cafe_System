package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryModel implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final Map<String, Integer> items = new HashMap<>();

    public void restock(String item, int amount) {
        items.put(item, items.getOrDefault(item, 0) + amount);
        notifyObservers();
    }

    public Map<String, Integer> getInventory() { return items; }

    @Override public void addObserver(Observer o){ observers.add(o); }
    @Override public void removeObserver(Observer o){ observers.remove(o); }
    @Override public void notifyObservers(){ for(Observer o: observers) o.update(); }
}
