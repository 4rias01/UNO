package com.example.myuno.model.saves.planetext;

public interface IPlaneTextFileHandler {
    void writeToFile(String fileName, String content);
    String[] readFromFile(String fileName);
}
