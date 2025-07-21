package com.example.myuno.controller;

import com.example.myuno.view.managers.AnimationsManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HomeController {
    @FXML
    ImageView textBox;
    @FXML
    ImageView title;

    @FXML
    public void initialize() {
        AnimationsManager.blindingEffect(textBox, 0.1);
        AnimationsManager.fadeInEffect(title);
    }
}
