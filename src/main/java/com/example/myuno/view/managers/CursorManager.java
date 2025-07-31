/**
 * Manages custom mouse cursors within the application by loading cursor
 * images and providing methods to apply or retrieve them for various UI states.
 * <p>
 * Cursors are loaded statically from resource files and cached for performance.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy.
 * @version 1.0
 */

package com.example.myuno.view.managers;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
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
    /**
     * Applies the default custom cursor to the entire scene.
     * <p>
     * This method sets the scene's cursor to the pre-loaded default cursor,
     * ensuring a consistent appearance across all UI elements.
     * </p>
     *
     * @param scene the JavaFX Scene on which to set the global cursor
     * @version 1.0
     */
    public static void applyGlobalCursor(Scene scene) {
        scene.setCursor(cursorDefault);
    }


    /**
     * Loads an ImageCursor from the specified filename within the application resources,
     * using the provided hotspot coordinates.
     * <p>
     * This method retrieves the image from the "/com/example/myuno/images/cursor/"
     * resource path, creates an ImageCursor, and returns it. Throws a
     * NullPointerException if the resource cannot be found.
     * </p>
     *
     * @param filename the image file name located in the cursor resource directory
     * @param hotspotX the X coordinate of the cursor hotspot within the image
     * @param hotspotY the Y coordinate of the cursor hotspot within the image
     * @return a fully constructed ImageCursor ready for use
     * @version 1.0
     */
    private static ImageCursor loadCursor(String filename, int hotspotX, int hotspotY) {
        return new ImageCursor(
                new Image(Objects.requireNonNull(CursorManager.class.getResourceAsStream("/com/example/myuno/images/cursor/" + filename))),
                hotspotX, hotspotY
        );
    }

    /**
     * Retrieves the default cursor instance.
     *
     * @return the default ImageCursor
     */
    public static ImageCursor getCursorDefault() { return cursorDefault; }

    /**
     * Retrieves the hover-state cursor instance.
     *
     * @return the hover ImageCursor
     */
    public static ImageCursor getCursorHover() { return cursorHover; }

    /**
     * Retrieves the pressed-state cursor instance.
     *
     * @return the pressed ImageCursor
     */
    public static ImageCursor getCursorPressed() { return cursorPressed; }
}

