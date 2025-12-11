package com.example.controller;

import com.example.model.InventoryModel;
import com.example.model.Observer;
import com.example.model.OrderModel;

public class CustomerController implements Observer {

    private OrderModel orderModel;
    private InventoryModel inventoryModel;

    public CustomerController() {
        // would inject models in real app
    }

    @Override
    public void update() {
        // refresh customer UI
    }

    public void showBeverages(){}
    public void showPastries(){}
    public void handleAddItem(){}
    public void handleSubmitOrder(){}
    public void handleClearOrder(){}
}
