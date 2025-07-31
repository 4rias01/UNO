package com.example.myuno;

import com.example.myuno.view.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {

        SceneManager.setStage(mainStage);
        SceneManager.switchTo("ProfileScene");

        mainStage.setTitle("MyUno");
        mainStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/com/example/myuno/images/assets/favicon.png"))));
        mainStage.show();
    }
}
