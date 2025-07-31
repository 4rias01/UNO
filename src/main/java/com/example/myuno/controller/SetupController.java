package com.example.myuno.controller;

import com.example.myuno.exceptions.GameLoadException;
import com.example.myuno.exceptions.SceneLoadException;
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

/**
 * Controller class for the setup scene in the MyUno application.
 * <p>
 * This scene allows the player to start a new local game, continue a saved one,
 * or access the profile screen. It also manages exit confirmation logic and animations.
 * </p>
 */
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

    /**
     * Initializes the setup scene by loading user profile data,
     * setting up animations and listeners, and hiding the exit dialog.
     */
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

    /**
     * Applies hover animations and effects to interactive buttons.
     */
    private void applyAnimations() {
        Manager.applyGenericEvents(hBoxExit);
        Manager.applyGenericEvents(yesButton);
        Manager.applyGenericEvents(noButton);
        Manager.applyGenericEvents(profileButton);

        setOnClickButton(localButton, localLabel);
        setOnClickButton(continueButton, continueLabel);
    }

    /**
     * Sets up the exit button to show the confirmation dialog when clicked.
     */
    private void setOnClickBack() {
        hBoxExit.setOnMouseClicked(event -> {
            setVisibleDialog(true);
        });
    }

    /**
     * Closes the application when the "Yes" button in the exit dialog is clicked.
     */
    @FXML
    private void setOnActionYesButton() {
        SceneManager.closeStage();
    }

    /**
     * Hides the exit dialog when the "No" button is clicked.
     */
    @FXML
    private void setOnActionNoButton() {
        setVisibleDialog(false);
    }

    /**
     * Handles the event of starting a new local game.
     * Saves user data and navigates to the game scene.
     *
     * @throws IOException if the scene cannot be loaded
     */
    @FXML
    private void handleLocalButton() throws IOException {
        GameFileHandler.createNewGame();
        ProfileFileHandler.createNewUser(userProfile);
        try{
            SceneManager.switchTo("GameScene");
        }catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the event of continuing a previously saved game.
     * Loads saved data and transitions to the game scene.
     *
     * @throws IOException if the game or scene cannot be loaded
     */
    @FXML
    private void handleContinueButton() throws IOException {
        UserProfile userProfile = ProfileFileHandler.loadUser(this.userProfile.getName());
        try{
        GameMaster gameMaster = GameFileHandler.loadGame(this.userProfile.getName());
        }catch(GameLoadException e){
            System.out.println(e.getMessage());
        }
        assert userProfile != null;
        ProfileManager.setCurrentProfile(userProfile);
        GameManager.setCurrentUser(userProfile.getName());
        try{
            SceneManager.switchTo("GameScene");
        }catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Navigates back to the profile selection scene.
     *
     * @throws IOException if the scene cannot be loaded
     */
    @FXML
    private void handleProfileButton() throws IOException {
        try{
            SceneManager.switchTo("ProfileScene");
        }catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Toggles the visibility and interactivity of the exit dialog and other buttons.
     *
     * @param visible true to show the dialog, false to hide it
     */
    private void setVisibleDialog(Boolean visible) {
        exitDialog.setVisible(visible);
        exitDialog.setDisable(!visible);
        localButton.setDisable(visible);
        continueButton.setDisable(visible);
        hBoxExit.setDisable(visible);
    }

    /**
     * Applies hover effects and animations to a button and its corresponding label.
     *
     * @param button the button to animate
     * @param label  the label to display on hover
     */
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
