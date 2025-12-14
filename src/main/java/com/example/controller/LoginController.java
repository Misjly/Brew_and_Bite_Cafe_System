package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String password = passwordField.getText();
    }
}
