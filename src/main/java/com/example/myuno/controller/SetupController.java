package com.example.myuno.controller;

import com.example.myuno.model.GameMaster;
import com.example.myuno.model.planeSerializableFiles.ISeriazableFileHandler;
import com.example.myuno.model.planeSerializableFiles.SerializableFileHandler;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.AnimationsManager;
import com.example.myuno.view.managers.CursorManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

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
    @FXML Button continueButton;
    @FXML Button newGameButton;

    @FXML
    private void initialize() {
        exitDialog.setVisible(false);
        exitDialog.setDisable(true);
        /*continueDialog.setVisible(false);
        continueDialog.setDisable(true);*/

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
        Object obj = file.deserialize("uno_game.ser");
        if( obj instanceof GameMaster){
            System.out.println("se encontro un archivo serializable");
            continueDialog.setVisible(true);
            continueDialog.setVisible(false);
        }else{
            SceneManager.switchTo("GameScene");
            System.out.println("No se encontro ningun fileHandler");
        }
    }

    @FXML
    private void handleContinueButton(){
        ISeriazableFileHandler file = new SerializableFileHandler();
        Object obj = file.deserialize("uno_game.ser");
        if( obj instanceof GameMaster){
            try{
                SceneManager.switchTo("GameScene");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("No se pudo cargar la partida");
            continueDialog.setVisible(false);
            continueDialog.setDisable(true);
        }

    }
    @FXML
    private void handleNewGameButton(){
        File file = new File("uno_game.ser");
        if(file.exists()){
            file.delete();
        }
        try{
            SceneManager.switchTo("GameScene");
        }catch (IOException e){
            e.printStackTrace();
        }
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
