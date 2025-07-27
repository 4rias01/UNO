package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class WildCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final ImagePattern frontImage;

    public WildCard(){
        this.color = Card.Color.BLACK;
        this.specialType = SpecialType.WILD;
        this.frontImage = setFrontImage(color);
    }

    public ImagePattern setFrontImage(Color color){
        return new ImagePattern(
                new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/special/wildCard/"
                                +color.name()+"png")).toExternalForm()));
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
    public SpecialType getType() {
        return specialType;
    }
}
