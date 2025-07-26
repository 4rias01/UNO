package com.example.myuno.model.card;

public interface Special {
    public enum SpecialType {
        ADDTWO,
        ADDFOUR,
        SKIP,
        WILD
    }
    SpecialType getType();
}
