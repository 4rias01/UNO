package com.example.myuno.model.player.factory;

import com.example.myuno.model.player.Player;

import java.io.Serializable;

public abstract class PlayerFactory implements Serializable {
    public abstract Player createPlayer();
}
