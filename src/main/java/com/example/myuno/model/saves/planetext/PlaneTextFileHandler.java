package com.example.myuno.model.saves.planetext;

import java.io.*;

public class PlaneTextFileHandler implements IPlaneTextFileHandler{

    @Override
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            System.out.println("socio, la cago escribiendo el archivo plano");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line.trim()).append(",");
            }
        } catch (IOException e){
            System.out.println("socio, la cago leyendo el archivo plano");
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}
