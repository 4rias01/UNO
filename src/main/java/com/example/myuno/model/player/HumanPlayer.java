package com.example.myuno.model.player;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.DrawFourCard;
import com.example.myuno.model.card.types.NumberCard;
import com.example.myuno.model.card.types.WildCard;

import java.util.ArrayList;

public class HumanPlayer extends AbstractPlayer {
    private Card selectedCard = null;

    public HumanPlayer() {
        deck.add(new DrawFourCard());
        deck.add(new DrawFourCard());
        deck.add(new DrawFourCard());
        deck.add(new DrawFourCard());
        deck.add(new NumberCard(1, Card.Color.RED));
    }

    @Override
    public Card playCard(GameContext context) {
        if (selectedCard != null) {
            Card chosen = selectedCard;
            selectedCard = null;
            deck.remove(chosen);
            return chosen;
        }
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

    public void selectCard(Card card) {
        this.selectedCard = card;
    }
}
