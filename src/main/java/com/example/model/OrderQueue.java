package com.example.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderQueue {

    private static OrderQueue instance;

    private Queue<String> orders;
    private List<String> currentOrder;
    private List<Observer> observers;

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

    // Customer actions
    public void addItem(String item) {
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

    public void submitOrder() {
        if (!currentOrder.isEmpty()) {
            orders.add(String.join(", ", currentOrder));
            currentOrder.clear();
            notifyObservers();
        }
    }

    // Barista actions
    public List<String> getOrders() {
        return new ArrayList<>(orders);
    }

    public void advanceStatus(String order) {
        notifyObservers();
    }

    public void completeOrder(String order) {
        orders.remove(order);
        notifyObservers();
    }
}