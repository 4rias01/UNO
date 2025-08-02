package com.example.myuno.model.card.factory;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A factory class for creating UNO cards, either randomly or in batches.
 * <p>
 * This class supports generating individual cards of various types (number, action, wild),
 * as well as building full random decks of a given size.
 * </p>
 */
public class CardFactory implements Serializable {
    private final Random random = new Random();

    private static final Card.Color[] COLORS = {
            Card.Color.RED, Card.Color.YELLOW, Card.Color.GREEN, Card.Color.BLUE
    };

    /**
     * Constructs a new CardFactory.
     */
    public CardFactory(){}

    /**
     * Creates a single random card.
     * <p>
     * The card can be a NumberCard (0–9), SkipCard, DrawTwoCard, WildCard, or DrawFourCard.
     * Wild cards are colorless by default.
     * </p>
     *
     * @return a randomly generated Card
     */
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

    /**
     * Creates a list of randomly generated cards with the specified size.
     *
     * @param size the number of cards to generate
     * @return an {@link ArrayList} of random cards
     */
    public ArrayList<Card> getRandomDeck(int size){
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < size; i++){
            deck.add(createRandomCard());
        }
        return deck;
    }

    public ArrayList<Card> getDefinedDeckOne(){
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new NumberCard(1, COLORS[1]));
        deck.add(new NumberCard(1, COLORS[1]));

        return deck;
    }

    /**
     * Selects a random color from the standard UNO colors.
     *
     * @return a randomly selected {@link Card.Color}
     */
    private Card.Color randomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }
}
