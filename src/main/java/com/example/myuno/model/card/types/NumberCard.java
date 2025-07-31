package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Number;
import javafx.scene.image.Image;

import java.util.Objects;

public class NumberCard implements Number, Card {
    private final int number;
    private final Color color;
    private final String pathImage;

    public NumberCard(int number, Color color) {
        this.number = number;
        this.color = color;
        pathImage = setPathImage(number, color);
    }

    public String setPathImage(int number, Color color) {
        String colorString = color.toString().toLowerCase();
        return "/com/example/myuno/images/cards/number/"
                +colorString +"/"+number+".png";
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
        if (card.getColor().equals(color)) {
            return true;
        } else if (card instanceof Number) {
            return ((NumberCard) card).getNumber() == number;
        }
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
