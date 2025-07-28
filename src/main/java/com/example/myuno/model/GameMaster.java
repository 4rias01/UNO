package com.example.myuno.model;

import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import com.example.myuno.model.card.factory.CardFactory;
import com.example.myuno.model.card.types.DrawFourCard;
import com.example.myuno.model.card.types.WildCard;
import com.example.myuno.model.machine.ThreadPlayMachine;
import com.example.myuno.model.player.factory.HumanPlayerFactory;
import com.example.myuno.model.player.factory.IAPlayerFactory;
import com.example.myuno.model.player.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Random;

public class GameMaster {
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;
    private ThreadPlayMachine threadPlayMachine;
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

    public void startMachineThread(HBox deckOfPlayerTwo, ImageView cardOnDeskView) {
        threadPlayMachine = new ThreadPlayMachine(this, playerTwo, cardOnDeskView, deckOfPlayerTwo);
        threadPlayMachine.start();
        threadPlayMachine.isDaemon();
    }

    public boolean playTurn(Card card) {
        Player current = context.getTurn() == GameContext.Turn.PLAYER1 ? playerOne : playerTwo;

        if (!card.canBePlayedOver(context.getLastCard())) {
            return false; // Carta no válida
        }

        if (!current.getDeck().contains(card)) {
            return false; // No tiene esa carta
        }

        current.getDeck().remove(card);
        setCardOnDesk(card);
        if(card instanceof Special specialCard){
            applyCardEffects(specialCard);
        }
        context.nextTurn();
        return true;
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
        Player nextPlayer = context.getTurn() == GameContext.Turn.PLAYER1? playerTwo : playerOne;
        System.out.println("entro a applyCardEffects");
        if(card instanceof Special specialCard){
            switch(specialCard.getType()) {
                case DRAWFOUR: nextPlayer.addRandomCards(4);
                    Card.Color chosenColor = chooseRandomColor();
                    ((DrawFourCard)card).changeColor(chosenColor);
                break;
                case DRAWTWO: nextPlayer.addRandomCards(2);
                    break;
                case WILD:
                    Card.Color chosenColor2 = chooseRandomColor();
                    ((WildCard)card).changeColor(chosenColor2);
                    break;
                case SKIP: context.nextTurn();
                default: break;
            }
        }
    }

    public GameContext getContext() {
        return context;
    }

    public Card.Color chooseRandomColor() {
        Card.Color[] colors = {Card.Color.RED, Card.Color.YELLOW, Card.Color.GREEN, Card.Color.BLUE};
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }


}
