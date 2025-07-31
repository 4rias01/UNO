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


    public ThreadPlayMachine(GameMaster game, Player machinePlayer) {
        this.game = game;
        this.machinePlayer = machinePlayer;
    }

    @Override
    public void run() {
        while (running) {
            if (game.getContext().getTurn() == GameContext.Turn.PLAYER2) {
                try {
                    Thread.sleep(1000);
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
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


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
