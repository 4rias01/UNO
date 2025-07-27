package com.example.myuno.model.player;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.factory.CardFactory;

import java.util.ArrayList;

public class IAPlayer extends AbstractPlayer {

    public IAPlayer() {
        super();
    }

    @Override
    public Card playCard(GameContext context) {
        return null;
    }



    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public ArrayList<Card> getDeck() {
        return deck;
    }

}
