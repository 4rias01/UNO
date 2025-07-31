package com.example.myuno.exceptions;

/**
 * Exception thrown when there is an error loading a scene in the graphic interface
 */
public class SceneLoadException extends RuntimeException {
    /**
     * Creates a new exception with the specifies message and cause
     * @param message the error message
     * @param cause the original cause of the error
     */
    public SceneLoadException(String message, Throwable cause) {
        super(message,cause);
    }
    /**
     * Creates a new exception with the specifies message
     * @param message the error message
     */
    public SceneLoadException(String message) {
        super(message);
    }
}
