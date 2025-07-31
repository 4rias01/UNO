package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;

public class DrawFourCard implements Card, Special {
    private  Color color;
    private final SpecialType specialType;
    private String pathImage;

    public DrawFourCard() {
        this.color = Color.BLACK;
        this.specialType = SpecialType.DRAWFOUR;
        this.pathImage = setPathImage(color);
    }

    public String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/drawFour/"+
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
        return true;
    }

    @Override
    public SpecialType getType() {
        return specialType;
    }

    public void changeColor(Color Newcolor){
        this.color = Newcolor;
        this.pathImage = setPathImage(Newcolor);
    }
}
