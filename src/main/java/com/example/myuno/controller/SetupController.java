package com.example.myuno.controller;

import com.example.myuno.model.gamelogic.game.GameFileHandler;
import com.example.myuno.model.gamelogic.game.GameManager;
import com.example.myuno.model.gamelogic.game.GameMaster;
import com.example.myuno.model.gamelogic.profile.ProfileFileHandler;
import com.example.myuno.model.gamelogic.profile.ProfileManager;
import com.example.myuno.model.gamelogic.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import com.example.myuno.view.managers.CursorManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SetupController {

    @FXML HBox hBoxExit;
    @FXML Button localButton;
    @FXML Label localLabel;
    @FXML Button continueButton;
    @FXML Label continueLabel;

    @FXML VBox exitDialog;
    @FXML Button yesButton;
    @FXML Button noButton;
    @FXML Button profileButton;
    @FXML Label profileLabel;

    UserProfile userProfile;

    @FXML
    private void initialize() {
        userProfile = ProfileManager.getCurrentProfile();
        exitDialog.setVisible(false);
        exitDialog.setDisable(true);
        profileButton.setGraphic(userProfile.getUserImage());
        profileLabel.setText(userProfile.getName());
        applyAnimations();
        setOnClickBack();
    }

    private void applyAnimations() {
        Manager.applyGenericEvents(hBoxExit);
        Manager.applyGenericEvents(yesButton);
        Manager.applyGenericEvents(noButton);
        Manager.applyGenericEvents(profileButton);

        setOnClickButton(localButton, localLabel);
        setOnClickButton(continueButton, continueLabel);
    }

    private void setOnClickBack() {
        hBoxExit.setOnMouseClicked(event -> {
            setVisibleDialog(true);
        });
    }

    @FXML
    private void setOnActionYesButton() {
        SceneManager.closeStage();
    }

    @FXML
    private void setOnActionNoButton() {
        setVisibleDialog(false);
    }

    @FXML
    private void handleLocalButton() throws IOException {
        GameFileHandler.createNewGame();
        ProfileFileHandler.createNewUser(userProfile);
        SceneManager.switchTo("GameScene");
    }

    @FXML
    private void handleContinueButton() throws IOException {
        UserProfile userProfile = ProfileFileHandler.loadUser(this.userProfile.getName());
        GameMaster gameMaster = GameFileHandler.loadGame(this.userProfile.getName());
        assert userProfile != null;
        ProfileManager.setCurrentProfile(userProfile);
        GameManager.setCurrentUser(userProfile.getName());
        SceneManager.switchTo("GameScene");
    }

    @FXML
    private void handleProfileButton() throws IOException {
        SceneManager.switchTo("ProfileScene");
    }

    private void setVisibleDialog(Boolean visible) {
        exitDialog.setVisible(visible);
        exitDialog.setDisable(!visible);
        localButton.setDisable(visible);
        continueButton.setDisable(visible);
        hBoxExit.setDisable(visible);
    }

    private void setOnClickButton(Button button, Label label) {
        button.setOnMouseEntered(e -> {
            button.setCursor(CursorManager.getCursorHover());
            AnimationsManager.translateAndScale(button, 0, -20, 1.05, 1.05);
            label.setOpacity(1.0);
        });

        button.setOnMouseExited(e -> {
            button.setCursor(CursorManager.getCursorDefault());
            AnimationsManager.translateAndScale(button, 0, 0, 1, 1);
            label.setOpacity(0);
        });
    }
}
