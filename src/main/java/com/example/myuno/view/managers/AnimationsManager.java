/**
 * Utility class providing common JavaFX animations for UI nodes,
 * such as translation, scaling, and fading effects.
 * <p>
 * This manager coordinates transitions and plays them on the specified nodes,
 * simplifying repeated animation patterns across the application.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy.
 * @version 1.0
 */

package com.example.myuno.view.managers;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationsManager {

    /**
     * Simultaneously translates and scales the given node over a short duration.
     * <p>
     * Creates a {@link TranslateTransition} to move the node to the target
     * X and Y coordinates, and a {@link ScaleTransition} to resize the node to
     * the specified scale factors. Both transitions run in parallel.
     * </p>
     *
     * @param node   the UI element to animate
     * @param posX   the target X position (in pixels) relative to the node's origin
     * @param posY   the target Y position (in pixels) relative to the node's origin
     * @param scaleX the factor by which the node's width will be scaled
     * @param scaleY the factor by which the node's height will be scaled
     * @version 1.0
     */
    public static void translateAndScale(Node node, double posX, double posY, double scaleX, double scaleY) {
        TranslateTransition move = new TranslateTransition(Duration.millis(200), node);
        move.setToX(posX);
        move.setToY(posY);

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), node);
        scale.setToX(scaleX);
        scale.setToY(scaleY);

        new ParallelTransition(move, scale).play();
    }
    /**
     * Applies a continuous fading (twinkle) effect to the specified node.
     * <p>
     * Utilizes a {@link FadeTransition} that oscillates the node's opacity between
     * fully visible and partially transparent indefinitely, with an initial delay.
     * </p>
     *
     * @param node        the UI element to animate
     * @param startSecond the delay in seconds before the fade effect begins
     */
    public static void blindingEffect(Node node, double startSecond) {
        FadeTransition twinkle = new FadeTransition(Duration.seconds(0.9), node);
        twinkle.setFromValue(1.0);
        twinkle.setToValue(0.6);
        twinkle.setCycleCount(FadeTransition.INDEFINITE);
        twinkle.setAutoReverse(true);
        twinkle.setDelay(Duration.seconds(startSecond));
        twinkle.play();
    }

    /**
     * Scales the node down from a large size into its natural dimensions,
     * creating a 'zoom-in' appearance.
     * <p>
     * Initializes the node at ten times its normal scale, then animates
     * it back to its original size using an ease-out interpolation.
     * </p>
     *
     * @param node the UI element to animate
     */
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
