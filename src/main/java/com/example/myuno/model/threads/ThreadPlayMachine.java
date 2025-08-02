/**
 * Executes the AI player's turn logic in a dedicated thread, automatically
 * playing a valid card or drawing when necessary. Integrates with the game
 * model and updates the JavaFX UI via {@link Platform#runLater(Runnable)}.
 * <p>
 * This class continually monitors the game context for the AI player's turn
 * and performs the appropriate action after a brief delay to simulate thinking.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */

package com.example.myuno.model.threads;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.gamelogic.game.GameMaster;
import com.example.myuno.model.player.Player;
import javafx.application.Platform;

import java.util.Random;

public class ThreadPlayMachine extends Thread {
    private final GameMaster game;
    private final Player machinePlayer;
    private volatile boolean running = true;

    /**
     * Constructs the machine-play thread with the given game context and player.
     *
     * @param game          the {@link GameMaster} managing game state and rules
     * @param machinePlayer the AI-controlled {@link Player} whose turn is automated
     */
    public ThreadPlayMachine(GameMaster game, Player machinePlayer) {
        this.game = game;
        this.machinePlayer = machinePlayer;
    }
    /**
     * Main loop that checks for the AI player's turn and triggers the play logic.
     * <p>
     * Sleeps briefly between checks to avoid busy-waiting. Upon detecting the AI
     * turn, schedules {@link #putCardOnTheTable()} to run on the JavaFX thread.
     * </p>
     */
    @Override
    public void run() {
        while (running) {
            if (game.getContext().getTurn() == GameContext.Turn.PLAYER2) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    try {
                        putCardOnTheTable();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Selects and plays a valid card if available; otherwise, draws a card after
     * a random delay and passes the turn. Updates the UI to reflect changes.
     *
     * @throws InterruptedException if the thread sleep is interrupted during draw delay
     * @version 1.0
     */
    private void putCardOnTheTable() throws InterruptedException {
            Card topCard = game.getCardOnDesk();
            Card cardToPlay = null;
            for (Card card : machinePlayer.getDeck()) {
                if (card.canBePlayedOver(topCard)) {
                    cardToPlay = card;
                    break;
                }
            }

            if (cardToPlay != null) {
                game.playTurn(cardToPlay);
                GameController.instance.renderCardOnDesk();
            } else {
                machinePlayer.addRandomCards(1);
                GameController.instance.renderUnoButton();
                game.passTurn();
            }
            GameController.instance.renderPlayerTwoDeck();
    }

    public void stopRunning() {
        running = false;
    }
}
