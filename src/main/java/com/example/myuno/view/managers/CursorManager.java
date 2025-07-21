package com.example.myuno.view.managers;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.Objects;

public class CursorManager {
    private static final ImageCursor cursorDefault;
    private static final ImageCursor cursorHover;
    private static final ImageCursor cursorPressed;

    static {
        cursorDefault = loadCursor("default.png", 0, 0);
        cursorHover = loadCursor("hover.png", 28, 28);
        cursorPressed = loadCursor("pressed.png", 28, 28);
    }

    private static ImageCursor loadCursor(String filename, int hotspotX, int hotspotY) {
        return new ImageCursor(
                new Image(Objects.requireNonNull(CursorManager.class.getResourceAsStream("/com/example/myuno/images/cursor/" + filename))),
                hotspotX, hotspotY
        );
    }


    public static ImageCursor getCursorDefault() { return cursorDefault; }
    public static ImageCursor getCursorHover() { return cursorHover; }
    public static ImageCursor getCursorPressed() { return cursorPressed; }
}

