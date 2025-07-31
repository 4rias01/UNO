package com.example.myuno.model.saves.profile;

import com.example.myuno.model.saves.planetext.PlaneTextFileHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ProfileManager{
    private static UserProfile currentProfile = new UserProfile("invitado", path());

    public static UserProfile getCurrentProfile(){
        return currentProfile;
    }
    public static void setCurrentProfile(UserProfile profile){
        if (profile != null) {
            currentProfile = profile;
        } else {
            System.out.println("parce, no se puede asignar un perfil nulo");
        }
    }
    public static String path(){
        return "/com/example/myuno/images/assets/ProfileScene/profilePic.jpg";
    }


}
