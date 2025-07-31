package com.example.myuno.model.saves.plane.profile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;

public class UserProfile implements Serializable {
    private final String name;
    private final String imagePath;

    public UserProfile(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
    public String getName(){
        return name;
    }
    public String getImagePath(){
        return imagePath;
    }
    public ImageView getUserImage(){
        return new ImageView(new Image(Objects.requireNonNull(ProfileManager.class.getResourceAsStream(getImagePath()))));
    }
    public String toFileString(){
        return name + "," + imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return name.equals(that.name);
    }
}
