package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.example.model.Inventory;
import com.example.model.Observer;

public class ManagerInventoryController implements Observer {

    @FXML
    private TableView<String> inventoryTable;

    @FXML
    private TextField amountField;

    private final Inventory inventory;

    public ManagerInventoryController() {
        inventory = Inventory.getInstance();
        inventory.registerObserver(this);
    }

    @Override
    public void update() {
        inventoryTable.getItems().setAll(inventory.getAllItems());
    }

    @FXML
    public void handleRestock() {
        String item = inventoryTable.getSelectionModel().getSelectedItem();
        if (item != null && !amountField.getText().isEmpty()) {
            int amount = Integer.parseInt(amountField.getText());
            inventory.restock(item, amount);
        }
    }
}
