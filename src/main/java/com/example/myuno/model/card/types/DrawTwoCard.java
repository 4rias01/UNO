package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents a "Draw Two" special card in the UNO game.
 * This card forces the next player to draw two cards and lose their turn.
 * It implements both {@link Card} and {@link Special} interfaces.
 *
 */
public class DrawTwoCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final String pathImage;

    /**
     * Constructs a DrawTwoCard with the specified color.
     *
     * @param color the color assigned to this card
     */
    public DrawTwoCard(Color color){
        this.color = color;
        this.specialType = SpecialType.DRAWTWO;
        this.pathImage = setPathImage(color);
    }

    /**
     * Returns the image path for this card based on its color.
     *
     * @param color the color of the card
     * @return the string path to the corresponding image
     */
    private String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/drawTwo/" +
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
     * Determines whether this Draw Two card can be played on top of another card.
     * It can be played if the other card is also a Draw Two card, or if the colors match.
     *
     * @param card the card currently on top of the discard pile
     * @return true if this card can be played over the given card; false otherwise
     */
    @Override
    public Boolean canBePlayedOver(Card card) {
        if (card instanceof DrawTwoCard){
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

