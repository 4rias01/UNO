package com.example.myuno.model.card.factory;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.*;

import java.util.ArrayList;
import java.util.Random;

public class CardFactory {
    private final Random random = new Random();

    private static final Card.Color[] COLORS = {
            Card.Color.RED, Card.Color.YELLOW, Card.Color.GREEN, Card.Color.BLUE
    };

    public CardFactory(){}

    public Card createRandomCard(){
        Card card = null;
        int number = random.nextInt(14);

        if(number < 10){
            card = new NumberCard(number, randomColor());
        }
        else{
            card = switch (number) {
                case 10 -> new SkipCard(randomColor());
                case 11 -> new DrawTwoCard(randomColor());
                case 12 -> new WildCard();
                case 13 -> new DrawFourCard();
                default -> throw new IllegalStateException("Unexpected value: " + number);
            };
        }
        return card;
    }

    public ArrayList<Card> getRandomDeck(int size){
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < size; i++){
            deck.add(createRandomCard());
        }
        return deck;
    }

    private Card.Color randomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }
}
