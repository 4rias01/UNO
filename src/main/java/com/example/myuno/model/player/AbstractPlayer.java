package com.example.myuno.model.player;

import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.factory.CardFactory;

import java.util.ArrayList;

public abstract class AbstractPlayer implements Player {
    protected ArrayList<Card> deck;
    protected CardFactory factory = new CardFactory();
    protected boolean hasSingUno = false;

    public AbstractPlayer() {
        deck = new ArrayList<>();
        //deck = factory.getRandomDeck(5);
    }

    public abstract Card playCard(GameContext context);

    public void addRandomCards(int size) {
        for (int i = 0; i < size; i++) {
            deck.add(factory.createRandomCard());
        }
    }

    public Card getCard(){
       return null;
    }

    public ArrayList<Card> getDeck(){
        return null;
    }

    public boolean hasOneCard() {
        return deck.size() == 1;
    }

    public boolean hasSingUno() {
        return hasSingUno;
    }

    public void singUno(boolean hasSing) {
        this.hasSingUno = hasSing;
    }
}
