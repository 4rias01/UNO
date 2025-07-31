package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;

/**
 * Represents a Wild Draw Four card in the UNO game.
 * <p>
 * This card can be played over any other card, allows the player to change the current color,
 * and forces the opponent to draw four cards.
 * </p>
 */
public class DrawFourCard implements Card, Special {
    private  Color color;
    private final SpecialType specialType;
    private String pathImage;

    /**
     * Constructs a new Draw Four card.
     * Initializes with color BLACK and sets the default image path.
     */
    public DrawFourCard() {
        this.color = Color.BLACK;
        this.specialType = SpecialType.DRAWFOUR;
        this.pathImage = setPathImage(color);
    }

    /**
     * Sets the image path based on the specified color.
     *
     * @param color the color to use in the image path
     * @return the image path string
     */
    public String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/drawFour/"+
                colorString+".png";
    }

    /**
     * Returns the image path of the card.
     *
     * @return the path to the card image
     */
    @Override
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Returns the current color of the card.
     *
     * @return the card's color
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Indicates that this card can always be played over any other card.
     *
     * @param card the card currently on the desk
     * @return true always, since wild cards are universally playable
     */
    @Override
    public Boolean canBePlayedOver(Card card) {
        return true;
    }

    /**
     * Returns the special type of this card.
     *
     * @return {@link SpecialType#DRAWFOUR}
     */
    @Override
    public SpecialType getType() {
        return specialType;
    }

    /**
     * Changes the color assigned to this card and updates its image path accordingly.
     *
     * @param newColor the new color to assign to the card
     */
    public void changeColor(Color newColor){
        this.color = newColor;
        this.pathImage = setPathImage(newColor);
    }
}
