package com.example.myuno.controller;

import com.example.myuno.exceptions.SceneLoadException;
import com.example.myuno.model.gamelogic.game.GameManager;
import com.example.myuno.model.gamelogic.profile.ProfileManager;
import com.example.myuno.model.gamelogic.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the profile selection scene.
 * <p>
 * Allows the user to input a name, choose an avatar, and confirm profile creation.
 * The profile is then stored and used throughout the game session.
 * </p>
 */
public class ProfileController {
    @FXML Button profileImageOne;
    @FXML Button profileImageTwo;
    @FXML Button profileImageThree;
    @FXML Button selectButton;
    @FXML TextField userNameTextField;

    String selectedPath;

    /**
     * Initializes the scene by applying effects to the buttons and
     * setting up the text field listener for validation.
     */
    @FXML
    void initialize() {
        Manager.applyGenericEvents(profileImageOne);
        Manager.applyGenericEvents(profileImageTwo);
        Manager.applyGenericEvents(profileImageThree);
        Manager.applyGenericEvents(selectButton);

        selectButton.setDisable(true);
        initTextFieldListener(userNameTextField);
    }

    /**
     * Handles the event when the first profile image is selected.
     */
    @FXML
    private void handleOneButton() {
        selectedPath = getStringPath(profileImageOne);
        updateSelectButtonState();
    }

    /**
     * Handles the event when the second profile image is selected.
     */
    @FXML
    private void handleTwoButton() {
        selectedPath = getStringPath(profileImageTwo);
        updateSelectButtonState();
    }

    /**
     * Handles the event when the third profile image is selected.
     */
    @FXML
    private void handleThreeButton() {
        selectedPath = getStringPath(profileImageThree);
        updateSelectButtonState();
    }

    /**
     * Handles the selection confirmation by creating a new user profile
     * and navigating to the setup scene.
     *
     * @throws IOException if the scene cannot be loaded
     */
    @FXML
    private void handleSelectButton() throws IOException {
        String playerName = userNameTextField.getText();
        ProfileManager.setCurrentProfile(new UserProfile(playerName, selectedPath));
        GameManager.setCurrentUser(playerName);
        try{
            SceneManager.switchTo("SetupScene");
        }catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Sets a listener on the text field to validate input.
     * Only allows letters (A-Z, a-z) and limits the name to 10 characters.
     *
     * @param textField the text field to apply the listener to
     */
    private void initTextFieldListener(TextField textField) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            String cleaned = newText.replaceAll("[^A-Za-z]", "");

            if (cleaned.length() > 10) {
                cleaned = cleaned.substring(0, 10);
            }

            if (!cleaned.equals(newText)) {
                textField.setText(cleaned);
            }

            updateSelectButtonState();
        });
    }

    /**
     * Enables or disables the "Select" button depending on whether
     * a valid name and avatar have been selected.
     */
    private void updateSelectButtonState() {
        String text = userNameTextField.getText().trim();
        boolean isNameValid = text.length() >= 3;
        boolean isAvatarSelected = selectedPath != null && !selectedPath.isEmpty();

        selectButton.setDisable(!(isNameValid && isAvatarSelected));
    }

    /**
     * Returns the image path corresponding to the selected avatar button.
     *
     * @param button the selected avatar button
     * @return the corresponding image path as a string
     */
    private String getStringPath(Button button) {
        String rutaImagen = null;

        if (button.getStyleClass().contains("button-one")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image2.png";
        } else if (button.getStyleClass().contains("button-two")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image3.png";
        } else if (button.getStyleClass().contains("button-three")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image4.png";
        }

        return rutaImagen;
    }
}