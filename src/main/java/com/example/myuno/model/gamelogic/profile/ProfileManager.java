package com.example.myuno.model.gamelogic.profile;

/**
 * Manages the currently active user profile in the UNO application.
 * <p>
 * This class provides centralized access to the {@link UserProfile} instance
 * associated with the current session, along with a default profile image path.
 * </p>
 */
public class ProfileManager {

    private static UserProfile currentProfile = new UserProfile("invitado", path());

    /**
     * Retrieves the currently active user profile.
     *
     * @return the current {@link UserProfile}
     */
    public static UserProfile getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Sets the current user profile for the session.
     * <p>
     * If the provided profile is {@code null}, the operation is aborted
     * and a warning message is printed to the console.
     * </p>
     *
     * @param profile the {@link UserProfile} to assign as current
     */
    public static void setCurrentProfile(UserProfile profile) {
        if (profile != null) {
            currentProfile = profile;
        } else {
            System.out.println("parce, no se puede asignar un perfil nulo");
        }
    }

    /**
     * Returns the default path to the profile image used in the profile scene.
     *
     * @return the string path to the default profile image
     */
    public static String path() {
        return "/com/example/myuno/images/assets/ProfileScene/profilePic.jpg";
    }
}

