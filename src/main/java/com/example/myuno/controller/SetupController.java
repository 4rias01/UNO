package com.example.myuno.controller;

import com.example.myuno.view.managers.AnimationsManager;
import com.example.myuno.view.managers.CursorManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SetupController {

    @FXML
    HBox hBoxExit;
    @FXML Button localButton;
    @FXML Button onlineButton;

    @FXML
    private void initialize() {
        applyCustomEffect(hBoxExit);
    }

    private void applyCustomEffect(HBox hBox) {
        Manager.applyAllEvents(hBox);
        Manager.applyAllEvents(localButton);
        Manager.applyAllEvents(onlineButton);
    }
}
