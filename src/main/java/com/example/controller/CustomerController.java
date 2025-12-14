package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import com.example.model.Inventory;
import com.example.model.Observer;
import com.example.model.OrderQueue;

public class CustomerController implements Observer {

    @FXML
    private ListView<String> itemList;

    @FXML
    private ListView<String> currentOrderList;

    @FXML
    private ChoiceBox<String> sizeBox;

    private final Inventory inventory;
    private final OrderQueue orderQueue;

    public CustomerController() {
        inventory = Inventory.getInstance();
        orderQueue = OrderQueue.getInstance();
        orderQueue.registerObserver(this);
    }

    @FXML
    public void initialize() {
        sizeBox.getItems().addAll("Small", "Medium", "Large");
        sizeBox.setValue("Medium");
        showBeverages();
    }

    @Override
    public void update() {
        currentOrderList.getItems().setAll(orderQueue.getCurrentOrder());
    }

    @FXML
    public void showBeverages() {
        itemList.getItems().setAll(inventory.getBeverages());
    }

    @FXML
    public void showPastries() {
        itemList.getItems().setAll(inventory.getPastries());
    }

    @FXML
    public void handleAddItem() {
        String item = itemList.getSelectionModel().getSelectedItem();
        String size = sizeBox.getValue();

        if (item != null) {
            orderQueue.addToCurrentOrder(item + " (" + size + ")");
        }
    }

    @FXML
    public void handleSubmitOrder() {
        orderQueue.submitCurrentOrder();
    }

    @FXML
    public void handleClearOrder() {
        orderQueue.clearCurrentOrder();
    }
}
