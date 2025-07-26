package com.example.myuno.view.managers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class Manager {
    public static void applyToAllButtons(Parent root) {
        root.lookupAll(".button").forEach(node -> {
            if (node instanceof Button button) {
                applyGenericEvents(button);
            }
        });
    }


    public static void applyGenericEvents(Node node) {
        applyCustomEvent(node, 0, 0, 1.1);
    }

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

