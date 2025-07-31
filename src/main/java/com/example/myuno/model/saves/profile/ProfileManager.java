package com.example.myuno.model.saves.profile;

import java.io.Serializable;

public class ProfileManager{
    private static UserProfile currentProfile = new UserProfile("invitado");

    public static UserProfile getCurrentProfile(){
        return currentProfile;
    }
    public static void setCurrentProfile(UserProfile profile){
        currentProfile = profile;
    }
}
