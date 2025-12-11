package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class OrderModel implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final List<String> orders = new ArrayList<>();

    public void addOrder(String order) {
        orders.add(order);
        notifyObservers();
    }

    public List<String> getOrders() { return orders; }

    @Override public void addObserver(Observer o){ observers.add(o); }
    @Override public void removeObserver(Observer o){ observers.remove(o); }
    @Override public void notifyObservers(){ for(Observer o: observers) o.update(); }
}
