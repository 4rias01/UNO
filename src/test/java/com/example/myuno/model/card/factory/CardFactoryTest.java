package com.example.myuno.model.card.factory;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {
    private CardFactory cardFactory;

    @BeforeEach
    public void setUp(){
        cardFactory = new CardFactory();
    }

    @Test
    public void CreatRandomCard_NotNull(){
        Card card = cardFactory.createRandomCard();
        assertNotNull(card);
    }

    @RepeatedTest(10)
    public void CreateRandomCard_returnValidCardType(){
        Card card = cardFactory.createRandomCard();
        assertTrue(
                card instanceof NumberCard ||
                        card instanceof SkipCard ||
                        card instanceof WildCard ||
                        card instanceof DrawFourCard ||
                        card instanceof DrawTwoCard
        );
    }
    @Test
    public void CreateRandomCardColorValid(){
        Card card = cardFactory.createRandomCard();
        if(
            card instanceof NumberCard ||
            card instanceof SkipCard ||
            card instanceof WildCard
        ){
            Card.Color color = card.getColor();
            List<Card.Color> list = List.of(Card.Color.RED,Card.Color.YELLOW,Card.Color.BLUE,Card.Color.GREEN);
            assertTrue(list.contains(color));

        }
    }

    @Test
    public void testGetRandomDeck_CorrectSize() {
        ArrayList<Card> deck = cardFactory.getRandomDeck(20);
        assertEquals(20, deck.size());
    }

    @Test
    public void testGetRandomDeck_AllCardsValid() {
        ArrayList<Card> deck = cardFactory.getRandomDeck(30);
        for (Card card : deck) {
            assertNotNull(card);
            assertTrue(card instanceof NumberCard ||
                            card instanceof SkipCard ||
                            card instanceof DrawTwoCard ||
                            card instanceof WildCard ||
                            card instanceof DrawFourCard
                    );
        }
    }



}