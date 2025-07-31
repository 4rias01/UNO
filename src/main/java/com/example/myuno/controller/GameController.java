package com.example.myuno.controller;

import com.example.myuno.exceptions.InvalidCardImageException;
import com.example.myuno.exceptions.SceneLoadException;
import com.example.myuno.model.gamelogic.game.GameContext.Turn;
import com.example.myuno.model.gamelogic.game.GameFileHandler;
import com.example.myuno.model.gamelogic.game.GameManager;
import com.example.myuno.model.gamelogic.game.GameMaster;
import com.example.myuno.model.card.Card;
import com.example.myuno.model.gamelogic.profile.ProfileFileHandler;
import com.example.myuno.model.player.Player;
import com.example.myuno.model.gamelogic.profile.ProfileManager;
import com.example.myuno.model.gamelogic.profile.UserProfile;
import com.example.myuno.view.SceneManager;
import com.example.myuno.view.managers.Manager;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GameController {
    public static GameController instance;
    private boolean gameOver = false;

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
    @FXML VBox gameOverDialog;
    @FXML Label lblGameOverMessage;

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
        this.setDisableUnoButton(true);

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
    }

    public void renderPlayerTwoDeck() {
        this.deckOfPlayerTwo.getChildren().clear();

        for (Card card : this.playerTwo.getDeck()) {
            ImageView image = new ImageView(Card.BACK_IMAGE.getImage());
            image.setPreserveRatio(true);
            image.setFitHeight(180.0);
            this.deckOfPlayerTwo.getChildren().add(image);
        }
        GameFileHandler.saveGame();
    }

    public void renderCardOnDesk() {
        this.cardOnDesk = this.game.getCardOnDesk();
        try{
            this.cardOnDeskView.setImage(this.createImageFromCard(this.cardOnDesk));
        }catch (InvalidCardImageException e){
            System.out.println(e.getMessage());
        }
        this.cardOnDeskView.setPreserveRatio(true);
        this.cardOnDeskView.setFitHeight(180.0);
    }

    public void renderUnoButton() {
        setDisableUnoButton(!playerOne.hasOneCard() && !playerTwo.hasOneCard());
    }

    public void setDisableUnoButton(boolean disable) {
        this.unoButton.setVisible(!disable);
        this.unoButton.setDisable(disable);
    }

    private Button createCardButton(Card card) {
        Button button = new Button();
        button.getStyleClass().add("card-button");
        button.setPrefSize(60.0, 90.0);

        try{
            ImageView imageView = new ImageView(createImageFromCard(card));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(180.0);
            button.setGraphic(imageView);
        }catch (InvalidCardImageException e){
            System.out.println(e.getMessage());
        }

        Manager.applyCustomEvent(button, -10, -40, 1.1);

        button.setOnAction((e) -> this.handleCardPlayed(card));
        return button;
    }

    private void handleCardPlayed(Card card) {
        if (gameOver) {return;}
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
        if (gameOver) {
            this.robberButton.setDisable(true);
            return;
        }
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
        playerOne.singUno(true);
        this.setDisableUnoButton(true);
    }

    @FXML
    private void handleBackButton() throws IOException {
        SceneManager.switchTo("SetupScene");
    }

    /**
     * Creates an image from the path of a Card.
     * @param card the card from which the image will be obtained.
     * @return the image corresponding to the card
     * @throws InvalidCardImageException if the image can not be loaded or the path is invalid
     */
    private Image createImageFromCard(Card card) {
        String path = card.getPathImage();
        try {
            URL UrlImage = Objects.requireNonNull(Card.class.getResource(path));
            Image image = new Image(UrlImage.toExternalForm());
            if (image.isError()){
                throw new InvalidCardImageException("Error al cargar la imagen desde "+ path);
            }
            return image;
        }catch (NullPointerException e){
            throw new InvalidCardImageException("Ruta nula o invalida al cargar la imagen desde " + path);
        }
    }

    @FXML
    private void onAcceptGameOver() throws IOException {
        GameFileHandler.deleteGameFile();
        ProfileFileHandler.deleteProfile(GameManager.getCurrentUser());
        try{
            SceneManager.switchTo("SetupScene");
        }catch (SceneLoadException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showGameOverMessage(String message) {
        gameOver = true;
        lblGameOverMessage.setText(message);
        gameOverDialog.setVisible(true);
        gameOverDialog.setDisable(false);

        FadeTransition ft = new FadeTransition(Duration.millis(400), gameOverDialog);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        Stage stage = (Stage) gameOverDialog.getScene().getWindow();
        stage.setOnCloseRequest(e -> {
            e.consume();
            GameFileHandler.deleteGameFile();
            ProfileFileHandler.deleteProfile(GameManager.getCurrentUser());
            GameManager.getGameMaster().stopAllThreads();
            System.out.println("Ventana cerrada después del game over.");
            try {
                SceneManager.switchTo("SetupScene");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


    }


}
