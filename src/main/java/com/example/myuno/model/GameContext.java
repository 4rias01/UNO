package com.example.myuno.model;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.card.Card;

public class GameContext {
    private Card lastCard;
    private Card.Color currentColor;
    public enum Turn{
        PLAYER1, PLAYER2
    }
    private Turn turn;

    public GameContext(Card lastCard, Card.Color currentColor, Turn turn) {
        this.lastCard = lastCard;
        this.currentColor = currentColor;
        this.turn = turn;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }

    public Card.Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Card.Color currentColor) {
        this.currentColor = currentColor;
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
    }
}
