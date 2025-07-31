package com.example.myuno.model.player;

import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.factory.CardFactory;

import java.util.ArrayList;

/**
 * An abstract base class representing a generic player in the UNO game.
 * <p>
 * Implements common behavior and attributes shared by all types of players,
 * such as managing the player's hand (deck), drawing cards, and tracking UNO declarations.
 * </p>
 */
public abstract class AbstractPlayer implements Player {

    /**
     * The player's current hand of cards.
     */
    protected ArrayList<Card> deck;

    /**
     * A factory used to generate cards for the player.
     */
    protected CardFactory factory = new CardFactory();

    /**
     * Indicates whether the player has declared "UNO".
     */
    protected boolean hasSingUno = false;

    /**
     * Constructs a new {@code AbstractPlayer} with a randomly generated initial hand of 5 cards.
     */
    public AbstractPlayer() {
        deck = factory.getRandomDeck(5);
    }

    /**
     * Defines the behavior for a player to play a card during their turn.
     * Must be implemented by subclasses.
     *
     * @param context the current game context
     * @return the card played, or null if no card is played
     */
    public abstract Card playCard(GameContext context);

    /**
     * Adds a specified number of randomly generated cards to the player's hand.
     *
     * @param size the number of cards to add
     */
    public void addRandomCards(int size) {
        for (int i = 0; i < size; i++) {
            deck.add(factory.createRandomCard());
        }
    }

    /**
     * Retrieves a card from the player's hand.
     * This method returns {@code null} by default and may be overridden in subclasses.
     *
     * @return the selected card or {@code null}
     */
    public Card getCard() {
        return null;
    }

    /**
     * Returns the player's current hand.
     * This method returns {@code null} by default and may be overridden in subclasses.
     *
     * @return the deck of cards or {@code null}
     */
    public ArrayList<Card> getDeck() {
        return null;
    }

    /**
     * Checks if the player has exactly one card remaining in their hand.
     *
     * @return {@code true} if the player has one card; {@code false} otherwise
     */
    public boolean hasOneCard() {
        return deck.size() == 1;
    }

    /**
     * Indicates whether the player has declared "UNO".
     *
     * @return {@code true} if the player has sung "UNO"; {@code false} otherwise
     */
    public boolean hasSingUno() {
        return hasSingUno;
    }

    /**
     * Sets the UNO declaration status for the player.
     *
     * @param hasSing {@code true} if the player has declared "UNO"; {@code false} otherwise
     */
    public void singUno(boolean hasSing) {
        this.hasSingUno = hasSing;
    }

    public ArrayList<Card> getOnlyTwoCards() {
        return new ArrayList<>();
    }
}


