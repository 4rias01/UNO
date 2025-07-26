package com.example.myuno.controller;

import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {
    private static Boolean isHome = true;

    @FXML
    ImageView textBox;
    @FXML
    ImageView title;
    @FXML AnchorPane homeAnchorPane;


    @FXML
    public void initialize() {
        addListenerToScene(homeAnchorPane);
        AnimationsManager.blindingEffect(textBox, 0.1);
        AnimationsManager.fadeInEffect(title);
    }

    private void addListenerToScene(AnchorPane anchorPane) {
        anchorPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if(isHome){
                        try {
                            isHome = false;
                            SceneManager.switchTo("SetupScene");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                newScene.setOnMouseClicked(event -> {
                    if(isHome){
                        try {
                            isHome = false;
                            SceneManager.switchTo("SetupScene");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

}
