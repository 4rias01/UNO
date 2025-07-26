package com.example.myuno.controller;

import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import javafx.event.EventHandler;
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
                // Creamos el filtro de tecla como objeto para poder removerlo luego
                EventHandler<KeyEvent> keyHandler = new EventHandler<>() {
                    @Override
                    public void handle(KeyEvent event) {
                        newScene.removeEventFilter(KeyEvent.KEY_PRESSED, this);
                        newScene.setOnMouseClicked(null); // también quitamos el mouse
                        switchToSetupScene();
                    }
                };

                newScene.addEventFilter(KeyEvent.KEY_PRESSED, keyHandler);

                newScene.setOnMouseClicked(event -> {
                    newScene.removeEventFilter(KeyEvent.KEY_PRESSED, keyHandler);
                    newScene.setOnMouseClicked(null);
                    switchToSetupScene();
                });
            }
        });
    }

    private void switchToSetupScene() {
        try {
            SceneManager.switchTo("SetupScene");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
