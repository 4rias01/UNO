package com.example.myuno.model.card;

/**
 * Represents the numeric behavior of a UNO card.
 * <p>
 * This interface should be implemented by any card that contains a numeric value,
 * such as standard number cards in the UNO game.
 * </p>
 */
public interface Number {

    /**
     * Retrieves the numeric value associated with the card.
     *
     * @return an integer representing the card's number
     */
    public int getNumber();
}

