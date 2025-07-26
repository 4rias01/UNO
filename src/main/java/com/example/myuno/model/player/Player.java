package com.example.myuno.model.player;

import com.example.myuno.model.card.Card;

import java.util.ArrayList;

public interface Player {
    ArrayList<Card> cards = null;
    public Boolean playCard(Card card, Card nextCard);
    public void addCard(Card card);
}
