package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

public class SkipCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final String pathImage;

    public SkipCard(Color color){
        this.color = color;
        this.specialType = SpecialType.SKIP;
        this.pathImage = setPathImage(color);
    }

    private String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/skip/"+
                colorString+".png";
    }

    @Override
    public String getPathImage() {
        return pathImage;
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
