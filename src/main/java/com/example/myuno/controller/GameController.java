package com.example.myuno.controller;

import com.example.myuno.model.GameContext.Turn;
import com.example.myuno.model.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;
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
    @FXML
    Button unoButton;

    private final GameMaster game = new GameMaster(true);

    @FXML
    public void initialize() {
        instance = this;

        this.playerOne = this.game.getPlayerOne();
        this.playerTwo = this.game.getPlayerTwo();

        Manager.applyGenericEvents(this.robberButton);
        Manager.applyGenericEvents(this.unoButton);
        this.setDisableRenderButton(true);

        this.renderCardOnDesk();
        this.renderPlayerOneDeck();
        this.renderPlayerTwoDeck();

        this.game.startMachineThread();
    }

    public void renderPlayerOneDeck() {
        this.deckOfPlayerOne.getChildren().clear();

        for (Card card : this.playerOne.getDeck()) {
            Button cardButton = this.createCardButton(card);
            this.deckOfPlayerOne.getChildren().add(cardButton);
        }
        this.renderUnoButton();
    }

    public void renderPlayerTwoDeck() {
        this.deckOfPlayerTwo.getChildren().clear();

        for (Card card : this.playerTwo.getDeck()) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180.0);
            this.deckOfPlayerTwo.getChildren().add(image);
        }
        this.renderUnoButton();
    }

    public void renderCardOnDesk() {
        this.cardOnDesk = this.game.getCardOnDesk();
        this.cardOnDeskView.setImage(this.cardOnDesk.getFrontImage());
        this.cardOnDeskView.setPreserveRatio(true);
        this.cardOnDeskView.setFitHeight(180.0);
    }

    public void renderUnoButton() {
        if (this.playerOne.hasOneCard() || this.playerTwo.hasOneCard()) {
            this.setDisableRenderButton(false);
        }
        else {
            this.setDisableRenderButton(true);
        }
    }

    private void setDisableRenderButton(boolean disable) {
        this.unoButton.setVisible(!disable);
        this.unoButton.setDisable(disable);
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
                this.robberButton.setDisable(false);

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

    @FXML
    private void handleUnoButton() {

    }

}
