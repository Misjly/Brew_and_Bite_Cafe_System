package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.model.Observer;
import com.example.model.OrderQueue;

public class BaristaController implements Observer {

    @FXML
    private ListView<String> orderList;

    private final OrderQueue orderQueue;

    public BaristaController() {
        orderQueue = OrderQueue.getInstance();
        orderQueue.registerObserver(this);
    }

    @Override
    public void update() {
        orderList.getItems().setAll(orderQueue.getOrders());
    }

    @FXML
    public void handleStatusChange() {
        String order = orderList.getSelectionModel().getSelectedItem();
        if (order != null) {
            orderQueue.advanceStatus(order);
        }
    }

    @FXML
    public void handleCompleteOrder() {
        String order = orderList.getSelectionModel().getSelectedItem();
        if (order != null) {
            orderQueue.completeOrder(order);
        }
    }
}
