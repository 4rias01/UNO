package com.example.myuno.view;

import com.example.myuno.view.managers.CursorManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneManager {
    private static Scene mainScene;
    private static Stage mainStage;

    /**
     * Sets up and displays the main stage of the application
     * <p>
     * This method loads the HomeScene FXML file, initializes the main scene,
     * applies global cursor and button animations, and sets the scene to full screen.
     *
     * @param stage the primary {@link Stage} where the scene will be set
     */
    public static void setStage(Stage stage) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(
                "/com/example/myuno/scenes/HomeSceneView/HomeScene.fxml"));

        try{
            root = loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainScene = new Scene(root);
        CursorManager.applyGlobalCursor(mainScene);

        stage.setScene(mainScene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        mainStage = stage;
    }

    /**
     * Switches the current scene to the one specified by {@code sceneName}.
     * <p>
     * This method loads the corresponding FXML file located at
     * {@code /scenes/[sceneName]View/[sceneName].fxml}, applies global cursor settings
     * and animations to all buttons, and updates the root of the main scene.
     *
     * @param sceneName the name of the scene to switch to (must match the FXML folder and file name)
     * @throws IOException if the FXML file cannot be found or loaded
     */
    public static void switchTo(String sceneName) throws IOException {
        Parent newRoot;
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(
                "/com/example/myuno/scenes/"+ sceneName + "View/" + sceneName + ".fxml"));

        try{
            newRoot = loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        CursorManager.applyGlobalCursor(mainScene);
        mainScene.setRoot(newRoot);
    }

    public static void closeStage() {
        mainStage.close();
    }

    private static Rectangle createColorRect(
            Color fxColor,
            com.example.myuno.model.card.Card.Color cardColor,
            Stage stageToClose,
            com.example.myuno.model.card.Card.Color[] selectedColor) {

        Rectangle rect = new Rectangle(150, 150, fxColor);
        rect.setArcWidth(30);
        rect.setArcHeight(30);
        rect.setOnMouseClicked(e -> {
            selectedColor[0] = cardColor;
            stageToClose.close();
        });

        rect.setStyle("""
        -fx-cursor: hand;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 20, 0.3, 2, 2);
        """);

        rect.setOnMouseEntered(e -> rect.setScaleX(1.1));
        rect.setOnMouseEntered(e -> rect.setScaleY(1.1));
        rect.setOnMouseExited(e -> rect.setScaleX(1));
        rect.setOnMouseExited(e -> rect.setScaleY(1));

        return rect;
    }

    public static com.example.myuno.model.card.Card.Color showColorSelectionWindow() {
        Stage colorStage = new Stage(StageStyle.TRANSPARENT);
        colorStage.initOwner(mainStage);
        colorStage.initModality(Modality.WINDOW_MODAL);

        HBox colorBox = new HBox(40);
        colorBox.setPadding(new Insets(40));
        colorBox.setAlignment(Pos.BOTTOM_CENTER);
        colorBox.setStyle("-fx-background-color: transparent;");

        final com.example.myuno.model.card.Card.Color[] selectedColor = {null};

        Rectangle red = createColorRect(Color.RED, com.example.myuno.model.card.Card.Color.RED, colorStage, selectedColor);
        Rectangle yellow = createColorRect(Color.YELLOW, com.example.myuno.model.card.Card.Color.YELLOW, colorStage, selectedColor);
        Rectangle green = createColorRect(Color.LIMEGREEN, com.example.myuno.model.card.Card.Color.GREEN, colorStage, selectedColor);
        Rectangle blue = createColorRect(Color.DODGERBLUE, com.example.myuno.model.card.Card.Color.BLUE, colorStage, selectedColor);

        colorBox.getChildren().addAll(red, yellow, green, blue);

        Scene scene = new Scene(colorBox);
        scene.setFill(Color.TRANSPARENT);

        colorStage.setScene(scene);
        colorStage.setWidth(2150);
        colorStage.setHeight(1125);
        colorStage.setAlwaysOnTop(true);
        colorStage.centerOnScreen();
        colorStage.showAndWait();

        return selectedColor[0] != null ? selectedColor[0] : com.example.myuno.model.card.Card.Color.RED;
    }


}

