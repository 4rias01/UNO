package com.example.myuno.model.gamelogic.game;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;

import java.io.Serializable;

/**
 * Maintains the current state of the UNO game.
 * <p>
 * This class stores the last card played, manages the players and their turns,
 * and tracks game-related transitions such as the player with only one card.
 * </p>
 */
public class GameContext implements Serializable {

    private Card lastCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player playerWithOneCard;
    private final Player playerOne;
    private final Player playerTwo;

    /**
     * Enum representing which player's turn it is.
     */
    public enum Turn {
        PLAYER1, PLAYER2
    }

    private Turn turn;

    /**
     * Constructs a GameContext with the initial state of the game.
     *
     * @param lastCard the last card played on the table
     * @param turn the current player's turn
     * @param playerOne the first player
     * @param playerTwo the second player
     */
    public GameContext(Card lastCard, Turn turn, Player playerOne, Player playerTwo) {
        this.lastCard = lastCard;
        this.turn = turn;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        setCurrentPlayer(turn);
        setNextPlayer(turn);
    }

    /**
     * Retrieves the last card played in the game.
     *
     * @return the last card on the discard pile
     */
    public Card getLastCard() {
        return lastCard;
    }

    /**
     * Updates the last card played in the game.
     *
     * @param lastCard the new last card to be set
     */
    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }

    /**
     * Gets the player whose turn it currently is.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the player whose turn is next.
     *
     * @return the next player
     */
    public Player getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Returns the player who currently has only one card in hand, if any.
     *
     * @return the player with one card, or null if none
     */
    public Player getPlayerWithOneCard() {
        return playerWithOneCard;
    }

    /**
     * Sets the player whose turn it currently is based on the turn enum.
     *
     * @param turn the current turn indicator
     */
    public void setCurrentPlayer(Turn turn) {
        currentPlayer = turn == Turn.PLAYER1 ? playerOne : playerTwo;
    }

    /**
     * Sets the next player based on the current turn.
     *
     * @param turn the current turn indicator
     */
    public void setNextPlayer(Turn turn) {
        nextPlayer = turn == Turn.PLAYER1 ? playerTwo : playerOne;
    }

    /**
     * Sets the player who has only one card left.
     *
     * @param playerWithOneCard the player to be marked as having one card
     */
    public void setPlayerWithOneCard(Player playerWithOneCard) {
        this.playerWithOneCard = playerWithOneCard;
    }

    /**
     * Returns the current turn indicator.
     *
     * @return the turn enum indicating which player's turn it is
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Switches the turn to the other player and triggers appropriate UI updates.
     * Also updates the current and next player references accordingly.
     */
    public void nextTurn() {
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

