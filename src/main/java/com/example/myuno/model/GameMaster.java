package com.example.myuno.model;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.factory.HumanPlayerFactory;
import com.example.myuno.model.player.factory.IAPlayerFactory;
import com.example.myuno.model.player.Player;

public class GameMaster {
    private Player playerOne;
    private Player playerTwo;
    private Card cartOnDesk;

    public GameMaster(Boolean playWithIA) {
        playerOne = new HumanPlayerFactory().createPlayer();
        playerTwo = playWithIA ?
                new IAPlayerFactory().createPlayer() :
                new HumanPlayerFactory().createPlayer();
    }
}
