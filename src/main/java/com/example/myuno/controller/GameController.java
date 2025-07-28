package com.example.myuno.controller;

import com.example.myuno.model.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.card.Special;
import com.example.myuno.model.player.Player;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameController {
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
        playerOne = game.getPlayerOne();
        playerTwo = game.getPlayerTwo();
        Manager.applyGenericEvents(robberButton);

        renderCardOnDesk();
        renderPlayerOneDeck();
        renderPlayerTwoDeck();
    }

    private void renderPlayerOneDeck() {
        deckOfPlayerOne.getChildren().clear();
        for (Card card : playerOne.getDeck()) {
            Button cardButton = createCardButton(card);
            deckOfPlayerOne.getChildren().add(cardButton);
        }
    }

    private void renderPlayerTwoDeck() {
        deckOfPlayerTwo.getChildren().clear();
        for (Card card : playerTwo.getDeck()) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180);
            deckOfPlayerTwo.getChildren().add(image);
        }
    }

    private void renderCardOnDesk() {
        cardOnDesk = game.getCardOnDesk();
        cardOnDeskView.setImage(cardOnDesk.getFrontImage());
        cardOnDeskView.setPreserveRatio(true);
        cardOnDeskView.setFitHeight(180);
    }

    private Button createCardButton(Card card) {
        Button button = new Button();
        button.getStyleClass().add("card-button");
        button.setPrefSize(60, 90);
        ImageView imageView = new ImageView(card.getFrontImage());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(180);

        button.setGraphic(imageView);
        Manager.applyCustomEvent(button, -10, -40, 1.1);
        button.setOnAction(e -> handleCardPlayed(card));

        return button;
    }

    private void handleCardPlayed(Card card) {

        if (card.canBePlayedOver(cardOnDesk)) {
            playerOne.getDeck().remove(card);
            game.setCardOnDesk(card);
            if(card instanceof Special specialCard){
                game.applyCardEffects(specialCard);
                renderPlayerTwoDeck();
            }
            renderPlayerOneDeck();
            renderCardOnDesk();
        } else {
            System.out.println("No puedes jugar esta carta.");
        }
    }

    @FXML
    private void handleRobberButton(){
        playerOne.addRandomCards(1);
        renderPlayerOneDeck();
    }
}
