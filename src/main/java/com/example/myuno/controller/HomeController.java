package com.example.myuno.controller;

import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {
    @FXML
    ImageView textBox;
    @FXML
    ImageView title;
    @FXML AnchorPane anchorPane;

    @FXML
    public void initialize() {
        addListenerToScene(anchorPane);
        AnimationsManager.blindingEffect(textBox, 0.1);
        AnimationsManager.fadeInEffect(title);
    }

    private void addListenerToScene(AnchorPane anchorPane) {
        anchorPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    try {
                        SceneManager.switchTo("SetupScene");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                newScene.setOnMouseClicked(event -> {
                    try {
                        SceneManager.switchTo("SetupScene");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }
}
