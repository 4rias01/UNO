package com.example.myuno.model.player.factory;

import com.example.myuno.model.player.HumanPlayer;
import com.example.myuno.model.player.Player;

public class HumanPlayerFactory extends PlayerFactory {
    @Override
    public Player createPlayer() {
        return new HumanPlayer();
    }
}
