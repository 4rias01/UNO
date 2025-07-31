package com.example.myuno.controller;

import com.example.myuno.model.gamelogic.game.GameManager;
import com.example.myuno.model.gamelogic.profile.ProfileManager;
import com.example.myuno.model.gamelogic.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProfileController {
    @FXML Button profileImageOne;
    @FXML Button profileImageTwo;
    @FXML Button profileImageThree;
    @FXML Button selectButton;
    @FXML TextField userNameTextField;

    String selectedPath;

    @FXML
    void initialize() {
        Manager.applyGenericEvents(profileImageOne);
        Manager.applyGenericEvents(profileImageTwo);
        Manager.applyGenericEvents(profileImageThree);
        Manager.applyGenericEvents(selectButton);

        selectButton.setDisable(true);
        initTextFieldListener(userNameTextField);
    }

    @FXML
    private void handleOneButton() {
        selectedPath = getStringPath(profileImageOne);
    }

    @FXML
    private void handleTwoButton() {
        selectedPath = getStringPath(profileImageTwo);
    }

    @FXML
    private void handleThreeButton() {
        selectedPath = getStringPath(profileImageThree);
    }

    @FXML
    private void handleSelectButton() throws IOException {
        String playerName = userNameTextField.getText();
        ProfileManager.setCurrentProfile(new UserProfile(playerName, selectedPath));
        GameManager.setCurrentUser(playerName);
        SceneManager.switchTo("SetupScene");
    }

    private void initTextFieldListener(TextField textField) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            String withoutNumbers = newText.replaceAll("[0-9,@]", "");
            String text = withoutNumbers.trim();

            if (text.length() > 10) {
                text = text.substring(0, 10);
            }

            if (!text.equals(newText)) {
                userNameTextField.setText(text);
            }

            selectButton.setDisable(text.length() <= 3 && (selectedPath == null || selectedPath.isEmpty()));
        });
    }

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