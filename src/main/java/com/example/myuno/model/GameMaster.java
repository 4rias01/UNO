package com.example.myuno.model;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import com.example.myuno.model.card.factory.CardFactory;
import com.example.myuno.model.card.types.DrawFourCard;
import com.example.myuno.model.card.types.WildCard;
import com.example.myuno.model.machine.ThreadPlayMachine;
import com.example.myuno.model.player.Player;
import com.example.myuno.model.player.factory.HumanPlayerFactory;
import com.example.myuno.model.player.factory.IAPlayerFactory;
import com.example.myuno.view.SceneManager;

import java.util.Random;

public class GameMaster {
    private final Player playerOne;
    private final Player playerTwo;
    private Card cartOnDesk;
    private final CardFactory cardFactory = new CardFactory();
    private final GameContext context;

    public GameMaster(Boolean playWithIA) {
        this.playerOne = new HumanPlayerFactory().createPlayer();
        this.playerTwo = playWithIA ?
                new IAPlayerFactory().createPlayer() :
                new HumanPlayerFactory().createPlayer();

        this.cartOnDesk = this.generateFirstCard();
        this.context = new GameContext(cartOnDesk, GameContext.Turn.PLAYER1, playerOne, playerTwo);
        startMachineThread();
    }

    private void startMachineThread() {
        ThreadPlayMachine threadPlayMachine = new ThreadPlayMachine(this, playerTwo);
        threadPlayMachine.start();
        threadPlayMachine.isDaemon();
    }

    public boolean playTurn(Card card) {
        Player current = context.getCurrentPlayer();

        if (!card.canBePlayedOver(context.getLastCard())) {
            System.out.println("Es imposile que leas esto");
            return false;
        }

        if (!current.getDeck().contains(card)) {
            System.out.println("Es imposile que leas esto");
            return false;
        }

        current.getDeck().remove(card);
        this.setCardOnDesk(card);
        if(card instanceof Special specialCard){
            this.applyCardEffects(specialCard);
            GameController.instance.renderPlayerOneDeck();
            GameController.instance.renderPlayerTwoDeck();
        }
        this.context.nextTurn();
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
        this.cartOnDesk = card;
        this.context.setLastCard(card);
    }

    private void applyCardEffects(Special card){
        Player nextPlayer = this.context.getNextPlayer();
        if(card instanceof Special specialCard){
            switch(specialCard.getType()) {
                case DRAWFOUR:
                    nextPlayer.addRandomCards(4);
                    Card.Color chosenColor = (context.getTurn() == GameContext.Turn.PLAYER1)
                            ? SceneManager.showColorSelectionWindow()
                            : chooseRandomColor();
                    ((DrawFourCard) card).changeColor(chosenColor);
                    this.context.nextTurn();

                break;
                case DRAWTWO: nextPlayer.addRandomCards(2);
                    this.context.nextTurn();
                    break;
                case WILD:
                    Card.Color chosenColor2 = (this.context.getTurn() == GameContext.Turn.PLAYER1)
                            ? SceneManager.showColorSelectionWindow()
                            : chooseRandomColor();
                    ((WildCard)card).changeColor(chosenColor2);
                    break;
                case SKIP: this.context.nextTurn();
                    break;
                default: break;
            }
        }
    }

    public GameContext getContext() {
        return this.context;
    }

    private Card.Color chooseRandomColor() {
        Card.Color[] colors = {Card.Color.RED, Card.Color.YELLOW, Card.Color.GREEN, Card.Color.BLUE};
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }

    public void passTurn() {
        this.context.nextTurn();
    }

}
