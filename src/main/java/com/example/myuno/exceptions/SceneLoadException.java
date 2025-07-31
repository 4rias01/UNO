package com.example.myuno.exceptions;

public class SceneLoadException extends RuntimeException {
    public SceneLoadException(String message, Throwable cause) {
        super(message,cause);
    }
    public SceneLoadException(String message) {
        super(message);
    }
}
