package com.example.myuno.controller;

import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Controller class for the home scene of the MyUno application.
 * <p>
 * This class initializes visual effects and listens for user input
 * (keyboard or mouse) to transition from the home screen to the setup scene.
 * </p>
 */
public class HomeController {
    private static Boolean isHome = true;

    @FXML
    ImageView textBox;
    @FXML
    ImageView title;
    @FXML AnchorPane homeAnchorPane;

    /**
     * Initializes the home screen components and animations.
     * Adds a listener to detect user interaction and handle scene transition.
     */
    @FXML
    public void initialize() {
        addListenerToScene(homeAnchorPane);
        AnimationsManager.blindingEffect(textBox, 0.1);
        AnimationsManager.fadeInEffect(title);
    }

    /**
     * Adds keyboard and mouse event listeners to the provided anchor pane's scene.
     * <p>
     * When the user presses a key or clicks anywhere on the home screen,
     * the application transitions to the setup scene.
     * </p>
     *
     * @param anchorPane the root pane of the home scene
     */
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
