package com.example.myuno.model.saves.profile;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private final String name;

    public UserProfile(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String toFileString(){
        return name;
    }
}
