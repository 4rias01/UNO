package com.example.myuno.model.Profiles;

import com.example.myuno.model.PlainTextFiles.IPlainTextFileHandler;
import com.example.myuno.model.PlainTextFiles.PlainTextFileHandler;

import java.io.File;

public class ProfileManager {
    private static UserProfile currentProfile;
    private static final String PROFILES_DIR = "profiles/";

    public static UserProfile loadProfile(String name){
        if(name == null || name.trim().isEmpty()){
            name = "invitado";
        }

        new File(PROFILES_DIR).mkdirs();
        String file = PROFILES_DIR + name + "_profile.csv";
        IPlainTextFileHandler fileHandler = new PlainTextFileHandler();

        try{
            if(new File(file).exists()){
                String[] data = fileHandler.readFromFile(file);
                if(data != null && data.length >= 3){
                    currentProfile = new UserProfile(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                    return currentProfile;
                }
            }

            currentProfile = new UserProfile(name,0,0);
            fileHandler.writeToFile(file,currentProfile.toFileString());
            return currentProfile;

        }catch(Exception e){
            System.out.println("error cargando perfil");
            currentProfile = new  UserProfile(name,0,0);
            return currentProfile;
        }
    }

    public static UserProfile getCurrentProfile(){
        if(currentProfile == null){
            currentProfile = new  UserProfile("Invitado",0,0);
        }
        return currentProfile;
    }

    public static void saveCurrentProfile(){
        UserProfile profile = getCurrentProfile();
        String fileName = PROFILES_DIR + profile.getName() + "_profiles.csv";
        new PlainTextFileHandler().writeToFile(fileName,profile.toFileString());
    }
}
