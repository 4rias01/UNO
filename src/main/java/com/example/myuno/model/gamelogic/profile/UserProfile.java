package com.example.myuno.model.gamelogic.profile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a user profile in the UNO application.
 * <p>
 * A user profile contains a name and a path to the profile image used for UI representation.
 * Profiles can be serialized and saved as part of user persistence.
 * </p>
 */
public class UserProfile implements Serializable {

    private final String name;
    private final String imagePath;

    /**
     * Constructs a new {@code UserProfile} with the specified name and image path.
     *
     * @param name the user's name
     * @param imagePath the path to the user's profile image
     */
    public UserProfile(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    /**
     * Returns the user's name.
     *
     * @return the name of the profile
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the path to the profile image.
     *
     * @return a string representing the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns an {@link ImageView} containing the user's profile image.
     *
     * @return an ImageView loaded from the specified image path
     */
    public ImageView getUserImage() {
        return new ImageView(new Image(
                Objects.requireNonNull(ProfileManager.class.getResourceAsStream(getImagePath()))
        ));
    }

    /**
     * Converts the profile to a comma-separated string format for file storage.
     *
     * @return a string in the format {@code name,imagePath}
     */
    public String toFileString() {
        return name + "," + imagePath;
    }

    /**
     * Compares this profile to another object for equality.
     * <p>
     * Two profiles are considered equal if they have the same name.
     * </p>
     *
     * @param o the object to compare
     * @return {@code true} if the given object is a {@code UserProfile} with the same name; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return name.equals(that.name);
    }
}
