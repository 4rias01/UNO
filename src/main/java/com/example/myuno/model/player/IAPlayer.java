package com.example.myuno.model.player;

import com.example.myuno.model.card.types.WildCard;
import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.NumberCard;

import java.util.ArrayList;

/**
 * Represents an AI-controlled player in the UNO game.
 * <p>
 * This class extends {@link AbstractPlayer} and provides logic for automatically
 * choosing and playing a card based on the current game context.
 * </p>
 */
public class IAPlayer extends AbstractPlayer {

    /**
     * Constructs a new {@code IAPlayer} with an initial hand of 5 random cards.
     */
    public IAPlayer() {
        super();
    }

    /**
     * Plays the first valid card in the deck that can be legally played on the current top card.
     * <p>
     * If no valid card is found, the AI player draws one random card.
     * </p>
     *
     * @param context the current game context
     * @return the card played, or {@code null} if no card was played
     */
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

    /**
     * Returns {@code null} since AI card selection does not rely on direct retrieval.
     *
     * @return {@code null}
     */
    @Override
    public Card getCard() {
        return null;
    }

    /**
     * Retrieves the AI player's current deck of cards.
     *
     * @return the list of cards in the player's hand
     */
    @Override
    public ArrayList<Card> getDeck() {
        return deck;
    }
}

