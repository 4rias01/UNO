package com.example.myuno.model.player;

import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a generic player in the UNO game.
 * <p>
 * This interface defines the core actions and properties that any player,
 * whether human or AI, must implement within the game logic.
 * </p>
 */
public interface Player extends Serializable {

    /**
     * Plays a card based on the current game context.
     *
     * @param context the current {@link GameContext} of the match
     * @return the {@link Card} played, or {@code null} if no valid card is played
     */
    public Card playCard(GameContext context);

    /**
     * Adds a specified number of randomly generated cards to the player's deck.
     *
     * @param size the number of cards to add
     */
    public void addRandomCards(int size);

    /**
     * Retrieves a card from the player's hand.
     * <p>
     * The behavior of this method may vary depending on the implementation.
     * </p>
     *
     * @return the selected {@link Card}, or {@code null} if not applicable
     */
    public Card getCard();

    /**
     * Retrieves the player's current hand.
     *
     * @return an {@link ArrayList} containing the player's cards
     */
    public ArrayList<Card> getDeck();

    /**
     * Determines if the player has exactly one card in hand.
     *
     * @return {@code true} if the player has one card; {@code false} otherwise
     */
    public boolean hasOneCard();

    /**
     * Checks whether the player has declared "UNO".
     *
     * @return {@code true} if the player has sung "UNO"; {@code false} otherwise
     */
    public boolean hasSingUno();

    /**
     * Sets the UNO declaration status for the player.
     *
     * @param hasSing {@code true} if the player has sung "UNO"; {@code false} otherwise
     */
    public void singUno(boolean hasSing);
}

