package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Number;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents a numeric card in the UNO game.
 * Each NumberCard has a color and a number between 0 and 9.
 * Implements both {@link Number} and {@link Card} interfaces.
 *
 */
public class NumberCard implements Number, Card {
    private final int number;
    private final Color color;
    private final String pathImage;

    /**
     * Constructs a NumberCard with the specified number and color.
     *
     * @param number the numeric value of the card (typically between 0 and 9)
     * @param color the color assigned to the card
     */
    public NumberCard(int number, Color color) {
        this.number = number;
        this.color = color;
        pathImage = setPathImage(number, color);
    }

    /**
     * Returns the image path corresponding to this number card.
     *
     * @param number the numeric value of the card
     * @param color the color of the card
     * @return the string path to the image representing this card
     */
    public String setPathImage(int number, Color color) {
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/number/"
                + colorString + "/" + number + ".png";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathImage() {
        return pathImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Determines whether this number card can be legally played over another card.
     * It can be played if either the colors match or the numeric values match.
     *
     * @param card the top card of the discard pile
     * @return true if this card can be played over the given card; false otherwise
     *
     */
    @Override
    public Boolean canBePlayedOver(Card card) {
        if (card.getColor().equals(color)) {
            return true;
        } else if (card instanceof Number) {
            return ((NumberCard) card).getNumber() == number;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }
}

