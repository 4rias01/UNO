package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Number;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class NumberCard implements Number, Card {
    private final int number;
    private final Color color;
    private final ImagePattern frontImage;

    public NumberCard(int number, Color color) {
        this.number = number;
        this.color = color;
        frontImage = setFrontImage(number, color);
    }

    public ImagePattern setFrontImage(int number, Color color) {
        return new ImagePattern(
                new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/number/"
                                +color.name()+number+".png")).toExternalForm()));
    }

    @Override
    public ImagePattern getFrontImage() {
        return frontImage;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
