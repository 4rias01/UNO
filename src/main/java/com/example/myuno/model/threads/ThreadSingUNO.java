package com.example.myuno.model.threads;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.gamelogic.game.GameContext;
import com.example.myuno.model.gamelogic.game.GameMaster;
import com.example.myuno.model.player.Player;
import javafx.application.Platform;

public class ThreadSingUNO extends Thread {
    private final Player playerOne;
    private final Player playerTwo;
    private boolean playerOneAlreadySingUno = false;
    private boolean playerTwoAlreadySingUno = false;
    private volatile boolean running = true;

    public ThreadSingUNO(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public void run() {
        while (running){
            Boolean canSing = canSingUnoButton();
            if (canSing != null) {
                try{
                    actualizeHasAlreadySingUno();
                    GameController.instance.setDisableUnoButton(false);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    if (canSing) {
                        singUNO(playerOne);
                    } if (!canSing) {
                        singUNO(playerTwo);
                    }
                    actualizeHasAlreadySingUno();
                });
            } else{
                actualizeHasAlreadySingUno();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void singUNO(Player player) {
        if(player == playerOne){
            if (!playerOne.hasSingUno()) {
                playerOne.addRandomCards(1);
                GameController.instance.setDisableUnoButton(true);
                GameController.instance.renderPlayerOneDeck();
            } else {
                playerOne.singUno(false);
                playerOneAlreadySingUno = true;
            }
        } else if (player == playerTwo) {
            if (!playerOne.hasSingUno()) {
                playerTwoAlreadySingUno = true;
                GameController.instance.setDisableUnoButton(true);
            }
            else{
                playerOne.singUno(false);
                playerTwo.addRandomCards(1);
                GameController.instance.setDisableUnoButton(true);
                GameController.instance.renderPlayerTwoDeck();
            }
        }
    }

    private void actualizeHasAlreadySingUno() {
        if (playerOne.getDeck().size() != 1){
            playerOneAlreadySingUno = false;
        }
        if (playerTwo.getDeck().size() != 1){
            playerTwoAlreadySingUno = false;
        }
    }

    private Boolean canSingUnoButton(){
        if(playerOne.getDeck().size()==1 && !playerOneAlreadySingUno){
            return true;
        } if (playerTwo.getDeck().size()==1 && !playerTwoAlreadySingUno) {
            return false;
        }
        return null;
    }

    public void stopRunning() {
        running = false;
    }
}
