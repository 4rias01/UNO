package com.example.myuno.model.player;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.card.Card;

import java.util.ArrayList;

public class IAPlayer extends AbstractPlayer {

    public IAPlayer() {
        super();
    }

    @Override
    public Card playCard(GameContext context) {
        Card topCard = context.getLastCard();
        for (Card card : deck) {
            if (card.canBePlayedOver(topCard)) {
                deck.remove(card);
                return card;
            }
        }
        this.addRandomCards(1);
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
