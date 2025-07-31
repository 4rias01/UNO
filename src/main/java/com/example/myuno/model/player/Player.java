package com.example.myuno.model.player;

import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;

import java.io.Serializable;
import java.util.ArrayList;

public interface Player extends Serializable {
    public Card playCard(GameContext context);
    public void addRandomCards(int size);
    public Card getCard();
    public ArrayList<Card> getDeck();
    public boolean hasOneCard();
    public boolean hasSingUno();
    public void singUno(boolean hasSing);
}
