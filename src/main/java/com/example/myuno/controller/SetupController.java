package com.example.myuno.controller;

import com.example.myuno.model.GameMaster;
import com.example.myuno.model.Profiles.ProfileManager;
import com.example.myuno.model.planeSerializableFiles.ISeriazableFileHandler;
import com.example.myuno.model.planeSerializableFiles.SerializableFileHandler;
import com.example.myuno.model.Profiles.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import com.example.myuno.view.managers.CursorManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class SetupController {

    @FXML HBox hBoxExit;
    @FXML Button localButton;
    @FXML Label localLabel;
    @FXML Button onlineButton;
    @FXML Label onlineLabel;

    @FXML VBox exitDialog;
    @FXML Button yesButton;
    @FXML Button noButton;

    @FXML VBox continueDialog;
    @FXML Button ContinueButton;
    @FXML Button newGameButton;

    @FXML Button profile;
    @FXML TextField nameTextField;
    @FXML Button playProfileButton;



    @FXML
    private void initialize() {
        exitDialog.setVisible(false);
        exitDialog.setDisable(true);
        continueDialog.setVisible(false);
        continueDialog.setDisable(true);

        applyAnimations();
        setOnClickBack();
    }

    private void applyAnimations() {
        Manager.applyGenericEvents(hBoxExit);
        Manager.applyGenericEvents(yesButton);
        Manager.applyGenericEvents(noButton);

        setOnClickButton(localButton, localLabel);
        setOnClickButton(onlineButton, onlineLabel);
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
        ISeriazableFileHandler file = new SerializableFileHandler();
        try{
            Object obj = file.deserialize("uno_game.ser");
            if (obj instanceof GameMaster) {
                System.out.println("se encontro una partida guardad");
                continueDialog.setVisible(true);
                continueDialog.setDisable(false);
            } else {
                SceneManager.switchTo("GameScene");
                System.out.println("No se encontro partida guardada");
            }
        }catch(Exception e){
            System.out.println("No se encontro partida guardada");
        }
    }

    @FXML
    private void handleContinueButton(){
        ISeriazableFileHandler file = new SerializableFileHandler();
        try {
            Object obj = file.deserialize("uno_game.ser");
            if (obj instanceof GameMaster) {
                try {
                    SceneManager.switchTo("GameScene");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se pudo cargar la partida");
                continueDialog.setVisible(false);
                continueDialog.setDisable(true);
            }
        }catch (Exception e){
            System.out.println("No se pudo cargar la partida");
        }

    }
    @FXML
    private void handleNewGameButton(){
        try {
            File file = new File("uno_game.ser");
            if (file.exists()) {
                file.delete();
            }
            try {
                SceneManager.switchTo("GameScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("no se encontro una partida");
        }
    }

    @FXML
    private void handleProfile() {
        nameTextField.setVisible(true);
        nameTextField.setDisable(false);
        playProfileButton.setVisible(true);
        playProfileButton.setDisable(false);
    }

    @FXML
    private void handlePlayProfileButton(){
        String name = nameTextField.getText().trim();
        if(name.isEmpty()) {
            System.out.println("Nombre vacío");
            return;
        }
        try {
            UserProfile user = ProfileManager.loadProfile(name);
            System.out.println("perfil cargado"+ user.toFileString());
        }catch (Exception e){
            System.out.println("No se pudo cargar el perfil");
        }
        playProfileButton.setText("Listo");
    }

    private void setVisibleDialog(Boolean visible) {
        exitDialog.setVisible(visible);
        exitDialog.setDisable(!visible);
        localButton.setDisable(visible);
        onlineButton.setDisable(visible);
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
