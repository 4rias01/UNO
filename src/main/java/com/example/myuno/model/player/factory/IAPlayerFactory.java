package com.example.myuno.model.player.factory;

import com.example.myuno.model.player.IAPlayer;
import com.example.myuno.model.player.Player;

public class IAPlayerFactory extends PlayerFactory {
    @Override
    public Player createPlayer() {
        return new IAPlayer();
    }
}
