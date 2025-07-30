package com.example.myuno.model;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;

public class GameContext {
    private Card lastCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player playerWithOneCard;
    private final Player playerOne;
    private final Player playerTwo;

    public enum Turn{
        PLAYER1, PLAYER2
    }
    private Turn turn;

    public GameContext(Card lastCard, Turn turn, Player playerOne, Player playerTwo) {
        this.lastCard = lastCard;
        this.turn = turn;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        setCurrentPlayer(turn);
        setNextPlayer(turn);
    }

    public Card getLastCard() {
        return lastCard;
    }

    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Player getNextPlayer(){
        return nextPlayer;
    }

    public Player getPlayerWithOneCard(){
        return playerWithOneCard;
    }

    public void setCurrentPlayer(Turn turn) {
        currentPlayer = turn == Turn.PLAYER1 ? playerOne : playerTwo;
    }

    public void setNextPlayer(Turn turn) {
        nextPlayer = turn == Turn.PLAYER1 ? playerTwo : playerOne;
    }

    public void setPlayerWithOneCard(Player playerWithOneCard) {
        this.playerWithOneCard = playerWithOneCard;
    }

    public Turn getTurn() {
        return turn;
    }


    public void nextTurn() { //Se cambió la función para agregar los turnos att.Nessa
        if (turn == Turn.PLAYER1) {
            turn = Turn.PLAYER2;
            GameController.instance.onPlayer2TurnStart();
        } else {
            turn = Turn.PLAYER1;
            GameController.instance.onPlayerTurnStart();
        }
        setCurrentPlayer(turn);
        setNextPlayer(turn);
    }
}
