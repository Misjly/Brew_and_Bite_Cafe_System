package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.model.Menu;
import com.example.model.Observer;

public class ManagerMenuController implements Observer {

    @FXML
    private ListView<String> menuList;

    private final Menu menu;

    public ManagerMenuController() {
        menu = Menu.getInstance();
        menu.registerObserver(this);
    }

    @Override
    public void update() {
        menuList.getItems().setAll(menu.getItems());
    }

    @FXML
    public void handleAdd() {
        menu.addItem("New Menu Item");
    }

    @FXML
    public void handleEdit() {
        String item = menuList.getSelectionModel().getSelectedItem();
        if (item != null) {
            menu.editItem(item);
        }
    }

    @FXML
    public void handleRemove() {
        String item = menuList.getSelectionModel().getSelectedItem();
        if (item != null) {
            menu.removeItem(item);
        }
    }
}
