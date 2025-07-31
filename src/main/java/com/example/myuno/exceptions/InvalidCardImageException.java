package com.example.myuno.exceptions;

/**
 * Exception throws when there is an error loading a card image
 */
public class InvalidCardImageException extends RuntimeException {
    /**
     * Creates a new exception with the specifies message
     * @param message the error message
     */
    public InvalidCardImageException(String message) {
        super(message);
    }
}
