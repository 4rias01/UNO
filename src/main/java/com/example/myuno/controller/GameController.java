package com.example.myuno.controller;

import com.example.myuno.model.GameContext.Turn;
import com.example.myuno.model.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameController {
    public static GameController instance;

    Player playerOne;
    Player playerTwo;
    Card cardOnDesk;

    @FXML
    ImageView cardOnDeskView;
    @FXML
    HBox deckOfPlayerOne;
    @FXML
    HBox deckOfPlayerTwo;
    @FXML
    Button robberButton;

    private final GameMaster game = new GameMaster(true);

    @FXML
    public void initialize() {
        instance = this;
        this.playerOne = this.game.getPlayerOne();
        this.playerTwo = this.game.getPlayerTwo();
        Manager.applyGenericEvents(this.robberButton);
        this.renderCardOnDesk();
        this.renderPlayerOneDeck();
        this.renderPlayerTwoDeck();

        if (this.game.getContext().getTurn() == Turn.PLAYER1) {
            Card topCard = this.game.getCardOnDesk();
            boolean hasPlayableCard = false;

            for (Card card : this.playerOne.getDeck()) {
                if (card.canBePlayedOver(topCard)) {
                    hasPlayableCard = true;
                    break;
                }
            }

            this.robberButton.setDisable(hasPlayableCard);
        }

        this.game.startMachineThread(this.deckOfPlayerTwo, this.cardOnDeskView);
    }


    public void renderPlayerOneDeck() {
        this.deckOfPlayerOne.getChildren().clear();
        for (Card card : this.playerOne.getDeck()) {
            Button cardButton = this.createCardButton(card);
            this.deckOfPlayerOne.getChildren().add(cardButton);
        }
    }

    public void renderPlayerTwoDeck() {
        this.deckOfPlayerTwo.getChildren().clear();

        for (Card card : this.playerTwo.getDeck()) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180.0);
            this.deckOfPlayerTwo.getChildren().add(image);
        }
    }

    private void renderCardOnDesk() {
        this.cardOnDesk = this.game.getCardOnDesk();
        this.cardOnDeskView.setImage(this.cardOnDesk.getFrontImage());
        this.cardOnDeskView.setPreserveRatio(true);
        this.cardOnDeskView.setFitHeight(180.0);
    }

    private Button createCardButton(Card card) {
        Button button = new Button();
        button.getStyleClass().add("card-button");
        button.setPrefSize(60.0, 90.0);

        ImageView imageView = new ImageView(card.getFrontImage());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(180.0);
        button.setGraphic(imageView);

        Manager.applyCustomEvent(button, -10, -40, 1.1);

        button.setOnAction((e) -> this.handleCardPlayed(card));
        return button;
    }

    private void handleCardPlayed(Card card) {

         if (this.game.getContext().getTurn() == Turn.PLAYER1) {
            boolean played = this.game.playTurn(card);
            if (played) {
                this.renderPlayerOneDeck();
                this.renderPlayerTwoDeck();
                this.renderCardOnDesk();

            } else {
                System.out.println("¡Carta inválida!");
            }
        }
    }

    @FXML
    private void handleRobberButton() {
        if (this.game.getContext().getTurn() == Turn.PLAYER1) {
            this.playerOne.addRandomCards(1);
            this.renderPlayerOneDeck();
            this.robberButton.setDisable(true);
            this.game.passTurn();
            this.robberButton.setDisable(false);
        }
    }

    public void onPlayerTurnStart() {
        this.renderPlayerOneDeck();
        this.renderPlayerTwoDeck();
        this.renderCardOnDesk();
        System.out.println("¡Es tu turno!");
        SceneManager.showTurnText("Turno del Jugador");
        this.robberButton.setDisable(false);

        Card topCard = this.game.getCardOnDesk();
        boolean hasPlayableCard = false;

        for (Card card : this.playerOne.getDeck()) {
            if (card.canBePlayedOver(topCard)) {
                hasPlayableCard = true;
                break;
            }
        }

        this.robberButton.setDisable(hasPlayableCard);


    }

    public void onPlayer2TurnStart() {
        SceneManager.showTurnText("Turno de la Maquina");
    }
}
