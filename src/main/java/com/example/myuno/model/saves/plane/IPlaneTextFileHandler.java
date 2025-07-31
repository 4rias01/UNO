package com.example.myuno.model.saves.plane;

public interface IPlaneTextFileHandler {
    void writeToFile(String fileName, String content);
    String[] readFromFile(String fileName);
}
