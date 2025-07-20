package com.example.myuno.view.managers;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;

public class AnimationsManager {
    private static final ImageCursor cursorDefault;
    private static final ImageCursor cursorHover;
    private static final ImageCursor cursorPressed;

    static{
        cursorDefault = new ImageCursor(new Image(Objects.requireNonNull(AnimationsManager.class.getResourceAsStream("/com/example/myuno/images/cursor/default.png"))));
        cursorHover = new ImageCursor(new Image(Objects.requireNonNull(AnimationsManager.class.getResourceAsStream("/com/example/myuno/images/cursor/hover.png"))), 28, 28);
        cursorPressed = new ImageCursor(new Image(Objects.requireNonNull(AnimationsManager.class.getResourceAsStream("/com/example/myuno/images/cursor/pressed.png"))),28, 28);
    }

    public static void applyGlobalCursor(Scene scene) {
        scene.setCursor(cursorDefault);
    }

    public static void applyToAllButtons(Parent root) {
        root.lookupAll(".button").forEach(node -> {
            if (node instanceof Button button) {
                applyAllEvents(button);
            }
        });
    }

    public static void applyAllEvents(Node node) {
        node.setOnMouseEntered(e -> translateAndScale(node, 0, 0, 1.1, 1.1, cursorHover));
        node.setOnMouseExited(e -> translateAndScale(node, 0, 0, 1.0, 1.0, cursorDefault));
        node.setOnMousePressed(e -> node.setCursor(cursorPressed));
        node.setOnMouseReleased(e -> node.setCursor(cursorHover));
    }

    public static void applyCursorEvents(Node node){
        node.setOnMouseEntered(e ->  node.setCursor(cursorHover));
        node.setOnMouseExited(e -> node.setCursor(cursorDefault));
        node.setOnMousePressed(e -> node.setCursor(cursorPressed));
        node.setOnMouseReleased(e -> node.setCursor(cursorHover));

    }

    public static void translateAndScale(Node node, double posX, double posY, double scaleX, double scaleY, ImageCursor cursor) {
        node.setCursor(cursor);

        TranslateTransition move = new TranslateTransition(Duration.millis(200), node);
        move.setToX(posX);
        move.setToY(posY);

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), node);
        scale.setToX(scaleX);
        scale.setToY(scaleY);

        ParallelTransition animation = new ParallelTransition(move, scale);
        animation.play();

    }

    public static void blindingEffect(Node node, double startSecond) {
        FadeTransition twinkle = new FadeTransition(Duration.seconds(0.9), node);
        twinkle.setFromValue(1.0);
        twinkle.setToValue(0.6);
        twinkle.setCycleCount(FadeTransition.INDEFINITE);
        twinkle.setAutoReverse(true);
        twinkle.setDelay(Duration.seconds(startSecond));

        twinkle.play();
    }
}
