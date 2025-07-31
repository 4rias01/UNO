package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents a "Skip" special card in the UNO game.
 * When played, this card causes the next player to lose their turn.
 * Implements both {@link Card} and {@link Special} interfaces.
 *
 */
public class SkipCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final String pathImage;

    /**
     * Constructs a SkipCard with the specified color.
     *
     * @param color the color assigned to this card
     */
    public SkipCard(Color color){
        this.color = color;
        this.specialType = SpecialType.SKIP;
        this.pathImage = setPathImage(color);
    }

    /**
     * Returns the image path corresponding to this Skip card.
     *
     * @param color the color of the card
     * @return the string path to the appropriate image file
     */
    private String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/skip/" +
                colorString + ".png";
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
     * Determines whether this Skip card can be played on top of another card.
     * A Skip card may be played over another Skip card, or over a card with the same color.
     *
     * @param card the card currently at the top of the discard pile
     * @return true if this card can be legally played; false otherwise
     */
    @Override
    public Boolean canBePlayedOver(Card card) {
        if (card instanceof SkipCard){
            return true;
        }
        return card.getColor().equals(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecialType getType() {
        return specialType;
    }
}

