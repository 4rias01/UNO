package com.example.myuno.controller;

import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SetupController {

    @FXML HBox hBoxExit;
    @FXML VBox exitDialog;
    @FXML Button localButton;
    @FXML Button onlineButton;
    @FXML Button yesButton;
    @FXML Button noButton;
    @FXML Label title;
    @FXML Label message;

    @FXML
    private void initialize() {
        exitDialog.setVisible(false);
        exitDialog.setDisable(true);
        applyAnimations();
        setOnClickBack();
    }

    private void applyAnimations() {
        Manager.applyAllEvents(hBoxExit);
        Manager.applyAllEvents(localButton);
        Manager.applyAllEvents(onlineButton);
        Manager.applyAllEvents(yesButton);
        Manager.applyAllEvents(noButton);
    }

    private void setOnClickBack() {
        hBoxExit.setOnMouseClicked(event -> {
            exitDialog.setVisible(true);
            exitDialog.setDisable(false);
            localButton.setDisable(true);
            onlineButton.setDisable(true);
            hBoxExit.setDisable(true);

            setExitDialog();
        });
    }

    private void setExitDialog() {
        title.setText("Estas seguro?");
        message.setText("Presiona [SI] para salir de la aplicacion");
    }

    @FXML
    private void setOnActionYesButton() {
        SceneManager.closeStage();
    }

    @FXML
    private void setOnActionNoButton() {
        exitDialog.setVisible(false);
        exitDialog.setDisable(true);
        localButton.setDisable(false);
        onlineButton.setDisable(false);
        hBoxExit.setDisable(false);
    }
}
