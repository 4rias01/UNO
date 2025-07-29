package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

public class SkipCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final Image frontImage;

    public SkipCard(Color color){
        this.color = color;
        this.specialType = SpecialType.SKIP;
        this.frontImage = setFrontImage(color);
    }

    private Image setFrontImage(Color color){
        return new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/special/skip/"
                                +color.toString()+".png")).toExternalForm());
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
        if (card instanceof SkipCard){
            return true;
        }
        return card.getColor().equals(color);
    }

    @Override
    public SpecialType getType() {
        return specialType;
    }
}
