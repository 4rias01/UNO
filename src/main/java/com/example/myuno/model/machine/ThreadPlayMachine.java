package com.example.myuno.model.machine;

import com.example.myuno.model.GameContext;
import com.example.myuno.model.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ThreadPlayMachine extends Thread {
    private final GameMaster game;
    private final Player machinePlayer;
    private final ImageView tableImageView;
    private final HBox deckOfPlayerTwo;

    public ThreadPlayMachine(GameMaster game, Player machinePlayer, ImageView tableImageView, HBox deckOfPlayerTwo) {
        this.game = game;
        this.machinePlayer = machinePlayer;
        this.tableImageView = tableImageView;
        this.deckOfPlayerTwo = deckOfPlayerTwo;
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
                System.out.println("IA juega: " + cardToPlay);
                game.playTurn(cardToPlay);
                tableImageView.setImage(cardToPlay.getFrontImage());
            } else {
                System.out.println("IA no puede jugar. Roba una carta.");
                machinePlayer.addRandomCards(1);
            }
            renderMachineDeck();
    }

    private void renderMachineDeck() {
        deckOfPlayerTwo.getChildren().clear();
        for (int i = 0; i < machinePlayer.getDeck().size(); i++) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180);
            deckOfPlayerTwo.getChildren().add(image);
        }
    }
}
