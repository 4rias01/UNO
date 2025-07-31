package com.example.myuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a generic UNO card.
 * <p>
 * All specific card types (e.g., number cards, action cards, wild cards) implement this interface.
 * This interface defines the basic structure and essential behavior of any playable UNO card.
 * </p>
 */
public interface Card extends Serializable {

    /**
     * Enumeration of possible card colors used in the UNO game.
     * <p>
     * RED, GREEN, BLUE, and YELLOW correspond to the four standard playable colors.
     * BLACK is reserved for wild-type cards such as Wild and Draw Four.
     * </p>
     */
    public enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        BLACK
    }

    /**
     * Returns the path to the image file that graphically represents this card.
     *
     * @return a string containing the relative path to the image
     */
    public String getPathImage();

    /**
     * Returns the color associated with this card.
     *
     * @return the {@link Color} value of this card
     */
    public Color getColor();

    /**
     * Evaluates whether this card can be legally played over another card.
     * The logic varies by card type and may involve matching color, number, or type.
     *
     * @param card the card currently visible at the top of the discard pile
     * @return true if this card may be played over the given card; false otherwise
     */
    public Boolean canBePlayedOver(Card card);

    /**
     * Static constant representing the image pattern used for the back of all cards.
     * This is displayed when a card is hidden from the player (e.g., in the opponent's hand).
     */
    ImagePattern BACK_IMAGE = new ImagePattern(
            new Image(Objects.requireNonNull(Card.class.getResource(
                    "/com/example/myuno/images/cards/back.png")).toExternalForm())
    );
}

