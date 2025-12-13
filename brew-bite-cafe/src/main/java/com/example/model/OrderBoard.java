package com.example.model;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import com.example.enums.OrderStatus;


public class OrderBoard {
    private final Deque<Order> pendingOrders = new ArrayDeque<>();
    private final List<Order> fulfilledOrders = new ArrayList<>();


    public void addOrder(Order order) {
        if (order == null){
            throw new IllegalArgumentException("Order must not be null");
        }
        if(order.getStatus() != OrderStatus.PENDING){
            order.setStatus(OrderStatus.PENDING);

            }
        pendingOrders.addLast(order);
        
    }

    public void updateStatus(String orderId, OrderStatus newStatus) {
        if (orderId == null || newStatus == null) {
            throw new IllegalArgumentException("Ordder Id and the New Status must not be null");
        }

        Order target = findPendingOrderById(orderId);
        if (target == null) {
            return;
        }
        target.setStatus(newStatus);
        if (newStatus == OrderStatus.FULFILLED) {
            pendingOrders.remove(target);
            fulfilledOrders.add(target);
        }
    }


    private Order findPendingOrderById(String orderId) {
        for (Order order : pendingOrders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getPendingOrders() {
        return Collections.unmodifiableList(new ArrayList<>(pendingOrders));
    }   


    public List<Order> getFulfilledOrders() {
        return Collections.unmodifiableList(new ArrayList<>(fulfilledOrders));
    }

    public void addFulfilled(Order order) {
    if (order == null) {
        throw new IllegalArgumentException("Order must not be null");
    }
    order.setStatus(OrderStatus.FULFILLED);
    fulfilledOrders.add(order);
}

}