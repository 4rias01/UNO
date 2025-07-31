package com.example.myuno.model.player;

import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.DrawFourCard;
import com.example.myuno.model.card.types.NumberCard;

import java.util.ArrayList;

/**
 * Represents a human player in the UNO game.
 * <p>
 * This class extends {@link AbstractPlayer} and provides interaction methods
 * for selecting and playing cards based on user input.
 * </p>
 */
public class HumanPlayer extends AbstractPlayer {

    /**
     * The card selected by the player to be played.
     */
    private Card selectedCard = null;

    /**
     * Constructs a new {@code HumanPlayer} with an initial hand of 5 random cards.
     */
    public HumanPlayer() {
        super();
    }

    /**
     * Attempts to play the card previously selected by the user.
     * <p>
     * If a card has been selected, it is removed from the deck and returned.
     * Otherwise, returns {@code null}.
     * </p>
     *
     * @param context the current game context
     * @return the card played, or {@code null} if no card was selected
     */
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

    /**
     * Returns {@code null} as human card selection is handled manually.
     *
     * @return {@code null}
     */
    @Override
    public Card getCard() {
        return null;
    }

    /**
     * Retrieves the human player's current deck of cards.
     *
     * @return the list of cards in the player's hand
     */
    @Override
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Stores the card selected by the human player for future play.
     *
     * @param card the selected card
     */
    public void selectCard(Card card) {
        this.selectedCard = card;
    }
}

