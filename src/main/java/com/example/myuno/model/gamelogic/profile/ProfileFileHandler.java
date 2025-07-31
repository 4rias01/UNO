package com.example.myuno.model.gamelogic.profile;

import com.example.myuno.model.saves.plane.PlaneTextFileHandler;

import java.io.*;

public class ProfileFileHandler {
    private static final PlaneTextFileHandler handler = new PlaneTextFileHandler();
    private static final String PROFILES_DIR = "profiles/";

    public static void createNewUser(UserProfile user) {
        String name = PROFILES_DIR + user.getName() + "_profile.csv";
        String contend = user.toFileString(); // esto genera: "Andres,imagenes/avatar1.png"
        handler.writeToFile(name, contend);
    }

    public static UserProfile loadUser(String name) {
        if(name == null || name.trim().isEmpty()){
            name = "invitado";
        }

        UserProfile user;

        new File(PROFILES_DIR).mkdirs();
        String file = PROFILES_DIR + name + "_profile.csv";

        try{
            if(new File(file).exists()) {
                String[] data = handler.readFromFile(file);
                if (data != null && data.length >= 2) {
                    user = new UserProfile(data[0], data[1]);
                    return user;
                }
            }
            user = new UserProfile(name, ProfileManager.path());
            handler.writeToFile(file, user.toFileString());
            return user;
        } catch (Exception e){
            System.out.println("error cargando perfil en ProfileFileHandler manin");
            user = new  UserProfile(name, ProfileManager.path());
            return user;
        }
    }

    public static void deleteProfile(String userName) {
        String fileName = "profiles/" + userName + "_profile.csv";
        File profileFile = new File(fileName);
        if (profileFile.exists()) {
            if (profileFile.delete()) {
                System.out.println("eliminado el perfil de " + userName);
            }else {
                System.out.println("No se pudo eliminar el perfil de " + userName);
            }
        }
    }
}
