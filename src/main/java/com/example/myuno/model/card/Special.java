package com.example.myuno.model.card;

public interface Special {
    public enum SpecialType {
        DRAWTWO,
        DRAWFOUR,
        SKIP,
        WILD
    }
    SpecialType getType();
}
