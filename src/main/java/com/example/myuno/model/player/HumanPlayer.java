package com.example.myuno.model.player;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.card.Card;

import java.util.ArrayList;

public class HumanPlayer extends AbstractPlayer {
    private Card selectedCard = null;

    public HumanPlayer() {
        super();
    }

    @Override
    public Card playCard(GameContext context) {
        while (selectedCard == null) {
            try {
                Thread.sleep(100); // Esperar input del jugador
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Card chosen = selectedCard;
        selectedCard = null;
        deck.remove(chosen);
        return chosen;
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
