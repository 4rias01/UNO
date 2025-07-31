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
 * Controller for the profile selection scene in the UNO application.
 * <p>
 * This class manages user interaction for selecting a profile image, entering a valid username,
 * and confirming profile creation. Once complete, the profile is saved and the scene transitions
 * to the setup phase.
 * </p>
 */
public class ProfileController {

    @FXML Button profileImageOne;
    @FXML Button profileImageTwo;
    @FXML Button profileImageThree;
    @FXML Button selectButton;
    @FXML TextField userNameTextField;

    /**
     * Stores the path of the currently selected profile image.
     */
    String selectedPath;

    /**
     * Initializes the controller after the FXML components are loaded.
     * <p>
     * Applies standard UI effects to buttons and disables the select button by default.
     * Also attaches a listener to the text field to validate input in real time.
     * </p>
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
     * Handles the action for selecting the first profile image.
     */
    @FXML
    private void handleOneButton() {
        selectedPath = getStringPath(profileImageOne);
        updateSelectButtonState();
    }

    /**
     * Handles the action for selecting the second profile image.
     */
    @FXML
    private void handleTwoButton() {
        selectedPath = getStringPath(profileImageTwo);
        updateSelectButtonState();
    }

    /**
     * Handles the action for selecting the third profile image.
     */
    @FXML
    private void handleThreeButton() {
        selectedPath = getStringPath(profileImageThree);
        updateSelectButtonState();
    }

    /**
     * Handles the final confirmation when the user clicks the select button.
     * <p>
     * Creates a new {@link UserProfile}, updates global state, and switches to the setup scene.
     * </p>
     *
     * @throws IOException if scene switching encounters an I/O error
     */
    @FXML
    private void handleSelectButton() throws IOException {
        String playerName = userNameTextField.getText();
        ProfileManager.setCurrentProfile(new UserProfile(playerName, selectedPath));
        GameManager.setCurrentUser(playerName);
        try {
            SceneManager.switchTo("SetupScene");
        } catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes a listener for the username text field to enforce input rules.
     * <p>
     * Input is automatically filtered to exclude digits, '@' characters, and limited to 10 characters.
     * The select button is updated in real time based on input validity.
     * </p>
     *
     * @param textField the text field to monitor
     */
    private void initTextFieldListener(TextField textField) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            String withoutNumbers = newText.replaceAll("[0-9,@]", "").trim();

            if (withoutNumbers.length() > 10) {
                withoutNumbers = withoutNumbers.substring(0, 10);
            }

            if (!withoutNumbers.equals(newText)) {
                userNameTextField.setText(withoutNumbers);
            }

            updateSelectButtonState();
        });
    }

    /**
     * Resolves the image path associated with a given button based on its style class.
     *
     * @param button the button representing a profile image option
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

    /**
     * Updates the enablement state of the select button.
     * <p>
     * The button is enabled only when a valid username (minimum 3 characters) is entered
     * and a profile image is selected.
     * </p>
     */
    private void updateSelectButtonState() {
        String text = userNameTextField.getText().replaceAll("[0-9,@]", "").trim();
        boolean isValidText = text.length() >= 3;
        boolean isImageSelected = selectedPath != null && !selectedPath.isEmpty();

        selectButton.setDisable(!(isValidText && isImageSelected));
    }
}
