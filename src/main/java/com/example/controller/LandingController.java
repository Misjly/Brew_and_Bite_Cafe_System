package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LandingController {

    @FXML
    private TextField nameField;

    @FXML
    public void handleCustomer() {
        String customerName = nameField.getText();
    }

    @FXML
    public void handleEmployeeLogin() {
    }
}
