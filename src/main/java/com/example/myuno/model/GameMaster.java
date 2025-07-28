package com.example.myuno.model;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import com.example.myuno.model.card.factory.CardFactory;
import com.example.myuno.model.card.types.NumberCard;
import com.example.myuno.model.card.types.WildCard;
import com.example.myuno.model.player.factory.HumanPlayerFactory;
import com.example.myuno.model.player.factory.IAPlayerFactory;
import com.example.myuno.model.player.Player;

import java.util.ArrayList;

public class GameMaster {
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;

    private Card cartOnDesk;
    private final CardFactory cardFactory = new CardFactory();
    private GameContext context;

    public GameMaster(Boolean playWithIA) {
        playerOne = new HumanPlayerFactory().createPlayer();
        playerTwo = playWithIA ?
                new IAPlayerFactory().createPlayer() :
                new HumanPlayerFactory().createPlayer();

        cartOnDesk = generateFirstCard();
        context = new GameContext(cartOnDesk, cartOnDesk.getColor(), GameContext.Turn.PLAYER1);
        currentPlayer = playerOne;
    }

    public void playTurn(Card card) {
        GameContext.Turn turn = context.getTurn();
    }

    private Card generateFirstCard() {
        Card card = cardFactory.createRandomCard();
        while (card instanceof Special) {
            card = cardFactory.createRandomCard();
        }
        return card;
    }

    public Player getPlayerOne() {
        return playerOne;
    }
    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Card getCardOnDesk() {
        return cartOnDesk;
    }

    public void setCardOnDesk(Card card) {
        cartOnDesk = card;
        context.setLastCard(card);
        context.setCurrentColor(card.getColor());

    }

    public void applyCardEffects(Special card){
        Player nextPlayer = context.getTurn() == GameContext.Turn.PLAYER1 ? playerTwo : playerOne;
        if(card instanceof Special specialCard){
            switch(specialCard.getType()) {
                case DRAWFOUR: nextPlayer.addRandomCards(4);
                break;
                case DRAWTWO: nextPlayer.addRandomCards(2); break;
                case WILD: break;
                default: break;
            }
        }
    }
}
