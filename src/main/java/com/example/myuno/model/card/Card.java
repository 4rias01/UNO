package com.example.myuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.Serializable;
import java.util.Objects;

public interface Card extends Serializable {
    public enum Color{
        RED,
        GREEN,
        BLUE,
        YELLOW,
        BLACK
    }

    public Image getFrontImage();
    public Color getColor();
    public Boolean canBePlayedOver(Card card);

    ImagePattern BACK_IMAGE = new ImagePattern(
            new Image(Objects.requireNonNull(Card.class.getResource(
                    "/com/example/myuno/images/cards/back.png")).toExternalForm())
    );
}
