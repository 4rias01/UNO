/**
 * Monitors players' deck sizes to enforce the UNO rule and trigger
 * appropriate actions when a player reaches one card. Continuously checks
 * both players, updates the UNO button state, and applies penalties or
 * acknowledgments based on whether UNO was called in time.
 * <p>
 * This thread-safe implementation leverages JavaFX's {@link Platform}
 * to update UI elements and ensures that each player can only trigger
 * the UNO notification once per single-card event.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */

package com.example.myuno.model.threads;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.player.Player;
import javafx.application.Platform;

public class ThreadSingUNO extends Thread {
    private final Player playerOne;
    private final Player playerTwo;
    private boolean playerOneAlreadySingUno = false;
    private boolean playerTwoAlreadySingUno = false;
    private volatile boolean running = true;
    /**
     * Constructs a new ThreadSingUNO to monitor two players for UNO events.
     *
     * @param playerOne the first player to monitor
     * @param playerTwo the second player to monitor
     */
    public ThreadSingUNO(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    /**
     * Main execution loop that periodically checks each player's deck size.
     * When a player has exactly one card, updates the UNO button state and
     * invokes UNO handling logic on the JavaFX application thread.
     */
    @Override
    public void run() {
        while (running){
            if (playerOne.getDeck().size()==1 || playerTwo.getDeck().size()==1){
                try{
                    actualiceHasAlreadySingUno();
                    GameController.instance.setDisableUnoButton(!canShowUnoButton());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    actualiceHasAlreadySingUno();
                    if(playerOne.getDeck().size()==1 && playerTwo.getDeck().size()==1){
                        singUNO(playerOne, playerTwo);
                    } else if (playerOne.getDeck().size()==1) {
                        singUNO(playerOne);
                    } else if (playerTwo.getDeck().size()==1) {
                        singUNO(playerTwo);
                    }
                });
            } else{
                actualiceHasAlreadySingUno();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Handles the UNO event for a single player, applying penalties or
     * acknowledgments based on whether UNO was called appropriately.
     *
     * @param player the player who has one card remaining
     */
    private void singUNO(Player player) {
        if(player == playerOne && !playerOneAlreadySingUno){
            if (!playerOne.hasSingUno()) {
                System.out.println("PlayerOne no canto uno, come cartas");
                playerOne.addRandomCards(2);
                GameController.instance.renderUnoButton();
                GameController.instance.renderPlayerOneDeck();
            } else {
                playerOne.singUno(false);
                playerOneAlreadySingUno = true;
                System.out.println("playerOne cantó UNO a tiempo, one no come cartas.");
            }
        } else if (player == playerTwo && !playerTwoAlreadySingUno) {
            if (!playerOne.hasSingUno()) {
                playerTwoAlreadySingUno = true;
                System.out.println("playerOne cantó UNO a destiempo, IA no come");
            }
            else{
                playerOne.singUno(false);
                playerTwo.addRandomCards(2);
                GameController.instance.renderUnoButton();
                GameController.instance.renderPlayerTwoDeck();
                System.out.println("player canto a tiempo, IA come");
            }
        }
    }

    /**
     * Handles the UNO event when both players have one card remaining.
     * Determines which player to process based on previous UNO triggers.
     *
     * @param playerOne the first player
     * @param playerTwo the second player
     */
    private void singUNO(Player playerOne, Player playerTwo) {
        System.out.println("entraste a esta chota");
        if (playerOneAlreadySingUno && !playerTwoAlreadySingUno) {
            singUNO(playerTwo);
        }
        else if (playerTwoAlreadySingUno && !playerOneAlreadySingUno) {
            singUNO(playerOne);
        }
    }
    /**
     * Resets the UNO trigger flags when a player's deck size changes
     * away from one card.
     */
    private void actualiceHasAlreadySingUno() {
        if (playerOne.getDeck().size() != 1){
            playerOneAlreadySingUno = false;
        }
        if (playerTwo.getDeck().size() != 1){
            playerTwoAlreadySingUno = false;
        }
    }
    /**
     * Determines if the UNO button should be enabled based on whether
     * a player with one card has not yet been processed.
     *
     * @return {@code true} if the UNO button may be shown; {@code false} otherwise
     */
    private boolean canShowUnoButton(){
        if(playerOne.getDeck().size()==1 && !playerOneAlreadySingUno){
            return true;
        } else if (playerTwo.getDeck().size()==1 && !playerTwoAlreadySingUno) {
            return true;
        }
        return false;
    }

    public void stopRunning() {
        running = false;
    }
}
