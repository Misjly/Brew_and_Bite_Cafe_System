package com.example.controller;

import com.example.model.Observer;
import com.example.model.OrderModel;

public class BaristaController implements Observer {

    private OrderModel orderModel;

    public BaristaController() {}

    @Override
    public void update() {
        // refresh barista queue display
    }

    public void handleStatusChange(){}
    public void handleCompleteOrder(){}
}
