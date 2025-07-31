package com.example.myuno.model.gamelogic.profile;

import com.example.myuno.model.saves.plane.PlaneTextFileHandler;

import java.io.*;

/**
 * Handles the creation, loading, and deletion of user profiles in the UNO application.
 * <p>
 * Profiles are stored as plain CSV files in the {@code profiles/} directory
 * and contain basic information such as the user's name and avatar path.
 * </p>
 */
public class ProfileFileHandler {

    private static final PlaneTextFileHandler handler = new PlaneTextFileHandler();
    private static final String PROFILES_DIR = "profiles/";

    /**
     * Creates a new user profile by writing the profile data to a CSV file.
     *
     * @param user the {@link UserProfile} instance to be saved
     */
    public static void createNewUser(UserProfile user) {
        String name = PROFILES_DIR + user.getName() + "_profile.csv";
        String contend = user.toFileString();
        handler.writeToFile(name, contend);
    }

    /**
     * Loads a user profile from a file.
     * <p>
     * If the profile file does not exist, a new one is created using the default avatar path.
     * If no name is provided, the method defaults to using "invitado".
     * </p>
     *
     * @param name the username to load
     * @return a valid {@link UserProfile} instance
     */
    public static UserProfile loadUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "invitado";
        }

        UserProfile user;

        new File(PROFILES_DIR).mkdirs();
        String file = PROFILES_DIR + name + "_profile.csv";

        try {
            if (new File(file).exists()) {
                String[] data = handler.readFromFile(file);
                if (data != null && data.length >= 2) {
                    user = new UserProfile(data[0], data[1]);
                    return user;
                }
            }
            user = new UserProfile(name, ProfileManager.path());
            handler.writeToFile(file, user.toFileString());
            return user;
        } catch (Exception e) {
            System.out.println("error cargando perfil en ProfileFileHandler manin");
            user = new UserProfile(name, ProfileManager.path());
            return user;
        }
    }

    /**
     * Deletes the profile file associated with the given username.
     *
     * @param userName the name of the user whose profile should be deleted
     */
    public static void deleteProfile(String userName) {
        String fileName = "profiles/" + userName + "_profile.csv";
        File profileFile = new File(fileName);
        if (profileFile.exists()) {
            if (profileFile.delete()) {
                System.out.println("eliminado el perfil de " + userName);
            } else {
                System.out.println("No se pudo eliminar el perfil de " + userName);
            }
        }
    }
}

