package com.example.myuno.model.machine;

import com.example.myuno.controller.GameController;
import com.example.myuno.model.GameContext;
import com.example.myuno.model.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;
import javafx.application.Platform;

public class ThreadPlayMachine extends Thread {
    private final GameMaster game;
    private final Player machinePlayer;

    public ThreadPlayMachine(GameMaster game, Player machinePlayer) {
        this.game = game;
        this.machinePlayer = machinePlayer;
    }

    @Override
    public void run() {
        while (true) {
            if (game.getContext().getTurn() == GameContext.Turn.PLAYER2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    putCardOnTheTable();
                });
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void putCardOnTheTable() {
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
                GameController.instance.renderUnoButton(machinePlayer);
                game.passTurn();
            }
            GameController.instance.renderPlayerTwoDeck();
    }
}
