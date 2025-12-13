package com.example.controller;

import com.example.model.InventoryModel;
import com.example.model.Observer;

public class ManagerInventoryController implements Observer {

    private InventoryModel inventoryModel;

    public ManagerInventoryController(){}

    @Override
    public void update() {
        // refresh table
    }

    public void handleRestock(){}
}
