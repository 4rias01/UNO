package com.example.myuno.model.saves.profile;

import com.example.myuno.model.saves.planetext.PlaneTextFileHandler;

import java.io.*;

public class ProfileFileHandler {
    private static final PlaneTextFileHandler handler = new PlaneTextFileHandler();

    public static void createNewUser(UserProfile user) {
        String name = user.getName() + ".csv";
        String contend = user.toFileString(); // esto genera: "Andres,imagenes/avatar1.png"
        handler.writeToFile(name, contend);
    }

    public static UserProfile loadUser(String name) {
        PlaneTextFileHandler handler = new PlaneTextFileHandler();
        String nombreArchivo = name + ".csv";
        String[] datos = handler.readFromFile(nombreArchivo);

        if (datos.length >= 2) {
            String nombre = datos[0];
            String path = datos[1];
            return new UserProfile(nombre, path);
        } else {
            System.out.println("parce, no encontré datos válidos en el archivo de " + name);
            return null;
        }
    }
}
