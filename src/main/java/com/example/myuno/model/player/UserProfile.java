package com.example.myuno.model.player;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String name;
    private int gamesPlayed;
    private int gamesWon;

    public UserProfile(String name, int gamesPlayed, int gamesWon){
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    public String getName(){
        return name;
    }
    public int getGamesPlayed(){
        return gamesPlayed;
    }
    public int getGamesWon(){
        return gamesWon;
    }



    public String toFileString(){
        return name + "," + gamesPlayed + "," + gamesWon;
    }
}
