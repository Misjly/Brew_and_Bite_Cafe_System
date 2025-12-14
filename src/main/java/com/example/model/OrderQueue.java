package com.example.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderQueue {

    private static OrderQueue instance;

    private final Queue<String> orders;
    private final List<String> currentOrder;
    private final List<Observer> observers;

    private OrderQueue() {
        orders = new LinkedList<>();
        currentOrder = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static OrderQueue getInstance() {
        if (instance == null) {
            instance = new OrderQueue();
        }
        return instance;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void addToCurrentOrder(String item) {
        currentOrder.add(item);
        notifyObservers();
    }

    public List<String> getCurrentOrder() {
        return new ArrayList<>(currentOrder);
    }

    public void clearCurrentOrder() {
        currentOrder.clear();
        notifyObservers();
    }

    public void submitCurrentOrder() {
        if (!currentOrder.isEmpty()) {
            orders.add(String.join(", ", currentOrder));
            currentOrder.clear();
            notifyObservers();
        }
    }

    public List<String> getOrders() {
        return new ArrayList<>(orders);
    }

    public void advanceStatus(String order) {
        // status tracking can be added later
        notifyObservers();
    }

    public void completeOrder(String order) {
        orders.remove(order);
        notifyObservers();
    }
}
