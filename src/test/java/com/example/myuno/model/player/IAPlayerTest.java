package com.example.myuno.model.player;


import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.types.DrawFourCard;
import com.example.myuno.model.card.types.NumberCard;
import com.example.myuno.model.gamelogic.game.GameContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IAPlayerTest {
    private IAPlayer iaPlayer;
    private GameContext context;
    private HumanPlayer player;

    @BeforeEach
    void setUp() {
        iaPlayer = new IAPlayer();
        player = new HumanPlayer();
    }

    @Test
    void testPlayCard_WhenHasPlayableCard() {

        Card topCard = new NumberCard(5,Card.Color.RED);
        GameContext context = new GameContext(topCard, GameContext.Turn.PLAYER2,player,iaPlayer);
        Card matchingCard = new NumberCard(2,Card.Color.RED);
        iaPlayer.getDeck().add(matchingCard);
        Card played = iaPlayer.playCard(context);
        assertTrue(played.canBePlayedOver(matchingCard));
    }

    @Test
    void testPlayCard_WhenNoPlayableCard() {
        Card topCard = new NumberCard(5, Card.Color.YELLOW);
        GameContext context = new GameContext(topCard, GameContext.Turn.PLAYER2,player,iaPlayer);

        // Carta que NO se puede jugar
        Card unplayable = new NumberCard(7, Card.Color.BLUE);
        iaPlayer.getDeck().add(unplayable);

        int before = iaPlayer.getDeck().size();
        Card played = iaPlayer.playCard(context);

        // Verificaciones
        assertNull(played); 
        assertTrue(iaPlayer.getDeck().size() > before); 
    }

    @Test
    void testPlayCard_WhenPlayableCardIsDrawFourTwo() {
        Card topCard = new NumberCard(2,Card.Color.RED);
        GameContext context = new GameContext(topCard, GameContext.Turn.PLAYER2,player,iaPlayer);
        Card matchingCard = new DrawFourCard();
        iaPlayer.getDeck().add(matchingCard);
        Card played = iaPlayer.playCard(context);
        assertTrue(played.canBePlayedOver(topCard));
    }

}