package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.model.Observer;
import com.example.model.OrderQueue;

public class BaristaController implements Observer {

    @FXML
    private ListView<String> orderList;

    private OrderQueue orderQueue;

    public BaristaController() {
        orderQueue = OrderQueue.getInstance();
        orderQueue.registerObserver(this);
    }

    @Override
    public void update() {
        orderList.getItems().setAll(orderQueue.getOrders());
    }

    public void handleStatusChange() {
        String order = orderList.getSelectionModel().getSelectedItem();
        if (order != null) {
            orderQueue.advanceStatus(order);
        }
    }

    public void handleCompleteOrder() {
        String order = orderList.getSelectionModel().getSelectedItem();
        if (order != null) {
            orderQueue.completeOrder(order);
        }
    }
}
