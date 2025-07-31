package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents a "Wild" card in the UNO game.
 * A WildCard allows the player to choose a new color when played.
 * It can be played over any card, regardless of color or type.
 * Implements both {@link Card} and {@link Special} interfaces.
 */
public class WildCard implements Card, Special {
    private Color color;
    private final SpecialType specialType;
    private String pathImage;

    /**
     * Constructs a WildCard with the default color BLACK.
     */
    public WildCard(){
        this.color = Color.BLACK;
        this.specialType = SpecialType.WILD;
        this.pathImage = setPathImage(color);
    }

    /**
     * Returns the path to the image representing this WildCard.
     *
     * @param color the color to generate the image path for
     * @return a string representing the image file path
     */
    public String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/wildCard/" +
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
     * {@inheritDoc}
     * A WildCard can always be played, regardless of the top card.
     */
    @Override
    public Boolean canBePlayedOver(Card card) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecialType getType() {
        return specialType;
    }

    /**
     * Changes the color of the WildCard and updates its image path accordingly.
     *
     * @param Newcolor the new color to assign to this WildCard
     */
    public void changeColor(Color Newcolor){
        this.color = Newcolor;
        this.pathImage = setPathImage(Newcolor);
    }
}

