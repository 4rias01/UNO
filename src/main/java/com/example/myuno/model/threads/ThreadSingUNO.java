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

    public ThreadSingUNO(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

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


    private void singUNO(Player playerOne, Player playerTwo) {
        System.out.println("entraste a esta chota");
        if (playerOneAlreadySingUno && !playerTwoAlreadySingUno) {
            singUNO(playerTwo);
        }
        else if (playerTwoAlreadySingUno && !playerOneAlreadySingUno) {
            singUNO(playerOne);
        }
    }

    private void actualiceHasAlreadySingUno() {
        if (playerOne.getDeck().size() != 1){
            playerOneAlreadySingUno = false;
        }
        if (playerTwo.getDeck().size() != 1){
            playerTwoAlreadySingUno = false;
        }
    }

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
