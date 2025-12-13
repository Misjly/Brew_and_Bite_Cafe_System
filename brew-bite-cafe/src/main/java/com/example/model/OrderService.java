package com.example.model;

import java.util.List;

public class OrderService {

    private final OrderBoard orderBoard;
    private final InventoryService inventoryService;

    public OrderService(OrderBoard orderBoard, InventoryService inventoryService) {
        if (orderBoard == null || inventoryService == null) {
            throw new IllegalArgumentException("OrderBoard and InventoryService cannot be null");
        }
        this.orderBoard = orderBoard;
        this.inventoryService = inventoryService;
    }

    public Order createOrder(String customerName, List<OrderItem> items) {
        return new Order(customerName, items);
    }

    public boolean canPlaceOrder(Order order) {
        return inventoryService.canFulfill(order);
    }

    public void placeOrder(Order order) {
        if (!inventoryService.canFulfill(order)) {
            throw new IllegalArgumentException(
                "Insufficient inventory to place the order " + order.getId()
            );
        }

        inventoryService.consumeFor(order);
        orderBoard.addOrder(order);
    }

    public OrderBoard getOrderBoard() {
        return orderBoard;
    }
}
