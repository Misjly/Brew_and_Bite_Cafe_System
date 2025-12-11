package com.example.model;
public class OrderService {

    private final OrderBoard orderBoard;
    private final Inventory inventory;


    public OrderService(OrderBoard orderBoard, Inventory inventory) {
        if (orderBoard == null || inventory == null) {
            throw new IllegalArgumentException("Order Board and Inventory cannot be null");
        } 

        this.orderBoard = orderBoard;
        this.inventory = inventory;
    }
    
    public Order createOrder(String customerName, java.util.List<OrderItem> items) {
        return new Order(customerName, items);
    }


    public boolean canPlaceOrder(Order order) {
        return inventory.canFulfill(order);
    }

    public void placeOrder(Order order) {
        if(!inventory.canFulfill(order)) {
            throw new IllegalArgumentException("Insufficient inventory to place the order " + order.getId());
        }
        inventory.consumeFor(order);
        orderBoard.addOrder(order);
    }

    public OrderBoard getOrderBoard() {
        return orderBoard;
    }

}