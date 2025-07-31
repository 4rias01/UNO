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
        updateSelectButtonState();
    }

    @FXML
    private void handleTwoButton() {
        selectedPath = getStringPath(profileImageTwo);
        updateSelectButtonState();
    }

    @FXML
    private void handleThreeButton() {
        selectedPath = getStringPath(profileImageThree);
        updateSelectButtonState();
    }

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

    private void updateSelectButtonState() {
        String text = userNameTextField.getText().replaceAll("[0-9,@]", "").trim();
        boolean isValidText = text.length() >= 3;
        boolean isImageSelected = selectedPath != null && !selectedPath.isEmpty();

        selectButton.setDisable(!(isValidText && isImageSelected));
    }

}