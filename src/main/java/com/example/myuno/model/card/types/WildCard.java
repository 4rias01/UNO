package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class WildCard implements Card, Special {
    private  Color color;
    private final SpecialType specialType;
    private  Image frontImage;

    public WildCard(){
        this.color = Card.Color.BLACK;
        this.specialType = SpecialType.WILD;
        this.frontImage = setFrontImage(color);
    }

    public Image setFrontImage(Color color){
        return new Image(Objects.requireNonNull(Card.class.getResource(
                        "/com/example/myuno/images/cards/special/wildCard/"
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
        return true;
    }

    @Override
    public SpecialType getType() {
        return specialType;
    }

    public void changeColor(Color Newcolor){
        this.color = Newcolor;
        this.frontImage =  setFrontImage(Newcolor);

    }
}
