package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

public class DrawTwoCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final String imagePath;

    public DrawTwoCard(Color color){
        this.color = color;
        this.specialType = SpecialType.DRAWTWO;
        this.imagePath = "/com/example/myuno/images/cards/special/drawTwo/"
                +color.toString()+".png";
    }

    private Image setFrontImage(Color color){
        return new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/special/drawTwo/"
                                +color.toString()+".png")).toExternalForm());
    }

    @Override
    public Image getFrontImage() {
        return new Image(Objects.requireNonNull(Card.class.getResource(imagePath)).toExternalForm());
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Boolean canBePlayedOver(Card card) {
        if (card instanceof DrawTwoCard){
            return true;
        }
        return card.getColor().equals(color);
    }

    @Override
    public SpecialType getType() {
        return specialType;
    }
}
