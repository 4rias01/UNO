package com.example.myuno.model.player;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.card.Card;

import java.util.ArrayList;

public interface Player {
    public Card playCard(GameContext context);
    public void addRandomCards(int size);
    public Card getCard();
    public ArrayList<Card> getDeck();
}
