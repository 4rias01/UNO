package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

public class DrawTwoCard implements Card, Special {
    private final Color color;
    private final SpecialType specialType;
    private final String pathImage;

    public DrawTwoCard(Color color){
        this.color = color;
        this.specialType = SpecialType.DRAWTWO;
        this.pathImage = setPathImage(color);
    }

    private String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/drawTwo/"+
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
