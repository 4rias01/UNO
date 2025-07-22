package com.example.myuno.view.managers;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationsManager {
    public static void translateAndScale(Node node, double posX, double posY, double scaleX, double scaleY) {
        TranslateTransition move = new TranslateTransition(Duration.millis(200), node);
        move.setToX(posX);
        move.setToY(posY);

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), node);
        scale.setToX(scaleX);
        scale.setToY(scaleY);

        new ParallelTransition(move, scale).play();
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

    public static void fadeInEffect(Node node) {
        node.setScaleX(10);
        node.setScaleY(10);
        ScaleTransition scale = new ScaleTransition(Duration.millis(800), node);
        scale.setToX(1);
        scale.setToY(1);
        scale.setInterpolator(Interpolator.EASE_OUT);
        scale.play();
    }
}
