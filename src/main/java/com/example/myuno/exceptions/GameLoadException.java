package com.example.myuno.exceptions;

/**
 * Exception thrown when loading a saved game fails
 */
public class GameLoadException extends RuntimeException {
    /**
     * Creates a new exception with the specifies message
     * @param message
     */
    public GameLoadException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specifies message and cause
     * @param message the error message
     * @param cause the original cause of the error
     */
    public GameLoadException(String message, Throwable cause) {
        super(message,cause);
    }
}
