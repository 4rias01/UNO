package com.example.myuno.controller;

import com.example.myuno.model.gamelogic.game.GameContext.Turn;
import com.example.myuno.model.gamelogic.game.GameFileHandler;
import com.example.myuno.model.gamelogic.game.GameManager;
import com.example.myuno.model.gamelogic.game.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.player.Player;
import com.example.myuno.model.gamelogic.profile.ProfileManager;
import com.example.myuno.model.gamelogic.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class GameController {
    public static GameController instance;

    Player playerOne;
    Player playerTwo;
    Card cardOnDesk;

    @FXML ImageView cardOnDeskView;
    @FXML HBox deckOfPlayerOne;
    @FXML HBox deckOfPlayerTwo;
    @FXML Button robberButton;
    @FXML Button unoButton;
    @FXML Button backButton;
    @FXML Label playerName;
    @FXML ImageView playerImage;

    private final GameMaster game = GameManager.getGameMaster();

    @FXML
    public void initialize() {
        instance = this;
        this.initFxmlElements();
        boolean hasPlayableCard = false;
        if (this.game.getContext().getTurn() == Turn.PLAYER1) {
            for (Card card : this.playerOne.getDeck()) {
                if (card.canBePlayedOver(cardOnDesk)) {
                    hasPlayableCard = true;
                    break;
                }
            }
        }
        this.robberButton.setDisable(hasPlayableCard);
    }

    private void initFxmlElements() {
        this.playerOne = this.game.getPlayerOne();
        this.playerTwo = this.game.getPlayerTwo();

        UserProfile user = ProfileManager.getCurrentProfile();
        playerImage.setImage(new Image(Objects.requireNonNull(ProfileManager.class.getResourceAsStream(user.getImagePath()))));
        playerName.setText(user.getName());
        Manager.applyGenericEvents(this.robberButton);
        Manager.applyGenericEvents(this.unoButton);
        Manager.applyGenericEvents(this.backButton);
        this.setDisableRenderButton(true);

        this.renderCardOnDesk();
        this.renderPlayerOneDeck();
        this.renderPlayerTwoDeck();
    }

    public void renderPlayerOneDeck() {
        this.deckOfPlayerOne.getChildren().clear();
        for (Card card : this.playerOne.getDeck()) {
            Button cardButton = this.createCardButton(card);
            this.deckOfPlayerOne.getChildren().add(cardButton);
        }
        this.renderUnoButton(this.playerOne);
    }

    public void renderPlayerTwoDeck() {
        this.deckOfPlayerTwo.getChildren().clear();

        for (Card card : this.playerTwo.getDeck()) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180.0);
            this.deckOfPlayerTwo.getChildren().add(image);
        }
        this.renderUnoButton(this.playerTwo);
        GameFileHandler.saveGame();
    }

    public void renderCardOnDesk() {
        this.cardOnDesk = this.game.getCardOnDesk();
        this.cardOnDeskView.setImage(this.createImageFromCard(this.cardOnDesk));
        this.cardOnDeskView.setPreserveRatio(true);
        this.cardOnDeskView.setFitHeight(180.0);
    }

    public void renderUnoButton(Player player) {
        setDisableRenderButton(!playerOne.hasOneCard() && !playerTwo.hasOneCard());
    }

    private void setDisableRenderButton(boolean disable) {
        this.unoButton.setVisible(!disable);
        this.unoButton.setDisable(disable);
    }

    private Button createCardButton(Card card) {
        Button button = new Button();
        button.getStyleClass().add("card-button");
        button.setPrefSize(60.0, 90.0);

        ImageView imageView = new ImageView(createImageFromCard(card));
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
                GameFileHandler.saveGame();
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

    @FXML
    private void handleUnoButton() {
        this.setDisableRenderButton(true);
    }

    @FXML
    private void handleBackButton() throws IOException {
        SceneManager.switchTo("SetupScene");
    }

    private Image createImageFromCard(Card card) {
        Image image = new Image(Objects.requireNonNull(Card.class.getResource(card.getPathImage())).toExternalForm());
        return image;
    }
}
