package com.example.myuno.view.managers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class Manager {
    public static void applyToAllButtons(Parent root) {
        root.lookupAll(".button").forEach(node -> {
            if (node instanceof Button button) {
                applyAllEvents(button);
            }
        });
    }


    public static void applyAllEvents(Node node) {
        node.setOnMouseEntered(e -> {
            AnimationsManager.translateAndScale(node, 0, 0, 1.1, 1.1);
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

