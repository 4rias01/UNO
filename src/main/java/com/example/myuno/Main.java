package com.example.myuno;

import com.example.myuno.view.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The entry point of the JavaFX application.
 * <p>
 * This class initializes the primary stage and sets the initial scene
 * of the application using {@link SceneManager}.
 * </p>
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * <p>
     * It sets the stage in the {@link SceneManager}, switches to the home scene,
     * sets the window title and icon, and then shows the stage.
     * </p>
     *
     * @param mainStage the primary stage for this application
     * @throws IOException if an I/O error occurs while loading the initial scene
     */
    @Override
    public void start(Stage mainStage) throws IOException {

        SceneManager.setStage(mainStage);
        SceneManager.switchTo("HomeScene");

        mainStage.setTitle("MyUno");
        mainStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/com/example/myuno/images/assets/favicon.png"))));
        mainStage.show();
    }
}
