package com.example.myuno.model.card;

/**
 * Represents the behavior of special cards in the UNO game.
 * <p>
 * This interface should be implemented by all cards that possess a special effect
 * beyond a standard numeric value (e.g., Skip, Draw Two, Wild, etc.).
 * </p>
 */
public interface Special {

    /**
     * Enumeration of all possible special card types in UNO.
     * <p>
     * DRAWTWO - Forces the next player to draw two cards and lose a turn. <br>
     * DRAWFOUR - Forces the next player to draw four cards and allows the player to choose a new color. <br>
     * SKIP - Skips the next player's turn. <br>
     * WILD - Allows the player to choose a new color without drawing cards. <br>
     * </p>
     */
    public enum SpecialType {
        DRAWTWO,
        DRAWFOUR,
        SKIP,
        WILD
    }

    /**
     * Returns the specific type of the special card.
     *
     * @return the {@link SpecialType} corresponding to this card
     */
    SpecialType getType();
}

