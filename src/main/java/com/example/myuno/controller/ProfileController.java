package com.example.myuno.controller;

import com.example.myuno.model.saves.profile.ProfileManager;
import com.example.myuno.model.saves.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class ProfileController {
    @FXML
    Button profileImageOne;
    @FXML
    Button profileImageTwo;
    @FXML
    Button profileImageThree;
    @FXML
    Button selectButton;
    @FXML
    TextField userNameTextField;

    ImageView selectedImage;

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
        selectedImage = getBackground(profileImageOne);
    }

    @FXML
    private void handleTwoButton() {
        selectedImage = getBackground(profileImageTwo);
    }

    @FXML
    private void handleThreeButton() {
        selectedImage = getBackground(profileImageThree);
    }

    @FXML
    private void handleSelectButton() throws IOException {
        String playerName = userNameTextField.getText();
        ProfileManager.setCurrentProfile(new UserProfile(playerName));
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

            selectButton.setDisable(text.length() <= 3);
        });
    }

    private ImageView getBackground(Button button) {
        String rutaImagen = null;

        if (button.getStyleClass().contains("button-one")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image2.png";
        } else if (button.getStyleClass().contains("button-two")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image3.png";
        } else if (button.getStyleClass().contains("button-three")) {
            rutaImagen = "/com/example/myuno/images/assets/ProfileScene/image4.png";
        }

        if (rutaImagen != null) {
            Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaImagen)));
            ImageView imageView = new ImageView(imagen);
            imageView.setFitWidth(180);
            imageView.setFitHeight(180);
            return imageView;
        }

        return null;
    }
}
