package com.example.myuno.model.card.types;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import javafx.scene.paint.ImagePattern;

public class SkipCard implements Card, Special {

    @Override
    public ImagePattern getFrontImage() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public SpecialType getType() {
        return null;
    }
}
