package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Number;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class NumberCard implements Number, Card {
    private final int number;
    private final Color color;
    private final Image frontImage;

    public NumberCard(int number, Color color) {
        this.number = number;
        this.color = color;
        frontImage = setFrontImage(number, color);
    }

    public Image setFrontImage(int number, Color color) {
        return new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/number/"
                                +color.toString()+"/"+number+".png")).toExternalForm());
    }

    @Override
    public Image getFrontImage() {
        return frontImage;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Boolean canBePlayedOver(Card card) {
        if (card.getColor().equals(color)) {
            return true;
        } else if (card instanceof Number) {
            return ((NumberCard) card).getNumber() == number;
        }
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
