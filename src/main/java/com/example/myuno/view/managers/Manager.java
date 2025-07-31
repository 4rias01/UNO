/**
 * Provides utility methods for applying common event handlers and animations
 * to JavaFX UI components, particularly buttons within a scene graph.
 * <p>
 * This manager enables bulk application of mouse interaction behaviors,
 * including hover, press, and release effects, by leveraging
 * {@link AnimationsManager} and {@link CursorManager}.
 * </p>
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy.
 * @version 1.0
 */
package com.example.myuno.view.managers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class Manager {
    /**
     * Applies generic mouse event handlers and visual effects to all buttons
     * found within the specified root component.
     * <p>
     * This method queries the scene graph for nodes with the ".button" style class,
     * casts them to {@link Button} instances, and attaches standard interaction
     * behaviors via {@link #applyGenericEvents(Node)}.
     * </p>
     *
     * @param root the parent node containing buttons to enhance with events
     * @version 1.0
     */
    public static void applyToAllButtons(Parent root) {
        root.lookupAll(".button").forEach(node -> {
            if (node instanceof Button button) {
                applyGenericEvents(button);
            }
        });
    }

    /**
     * Attaches default mouse event handlers and animations to a generic node.
     * <p>
     * By default, this method calls {@link #applyCustomEvent(Node, int, int, double)}
     * with no translation and a slight scale-up factor for hover effects.
     * </p>
     *
     * @param node the UI element to which generic events will be applied
     */
    public static void applyGenericEvents(Node node) {
        applyCustomEvent(node, 0, 0, 1.1);
    }
    /**
     * Attaches custom mouse interaction events to a node, enabling translation,
     * scaling, and cursor changes on mouse enter, exit, press, and release.
     * <p>
     * Uses {@link AnimationsManager#translateAndScale(Node, double, double, double, double)}
     * to animate the node and {@link CursorManager} to update the cursor state.
     * </p>
     *
     * @param node   the UI element to enhance with custom mouse events
     * @param posX   the X-axis translation offset for hover animations
     * @param posY   the Y-axis translation offset for hover animations
     * @param bulk   the scale factor to apply during hover

     * @version 1.0
     */
    public static void applyCustomEvent(Node node, int posX, int posY, double bulk) {
        node.setOnMouseEntered(e -> {
            AnimationsManager.translateAndScale(node, posX, posY, bulk, bulk);
            node.setCursor(CursorManager.getCursorHover());
        });

        node.setOnMouseExited(e -> {
            AnimationsManager.translateAndScale(node, 0, 0, 1.0, 1.0);
            node.setCursor(CursorManager.getCursorDefault());
        });

        node.setOnMousePressed(e -> node.setCursor(CursorManager.getCursorPressed()));
        node.setOnMouseReleased(e -> node.setCursor(CursorManager.getCursorHover()));
    }
}

