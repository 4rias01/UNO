package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;

import java.util.Objects;

public class WildCard implements Card, Special {
    private Color color;
    private final SpecialType specialType;
    private String pathImage;

    public WildCard(){
        this.color = Color.BLACK;
        this.specialType = SpecialType.WILD;
        this.pathImage = setPathImage(color);
    }

    public String setPathImage(Color color){
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/special/wildCard/"+
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
        this.pathImage =  setPathImage(Newcolor);

    }
}
