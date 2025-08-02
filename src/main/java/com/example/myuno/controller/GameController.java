package com.example.myuno.controller;

import com.example.myuno.exceptions.GameLoadException;
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

/**
 * Controller class for the main game scene in the MyUno application.
 * <p>
 * This class manages the UI components and game logic during the gameplay,
 * including rendering player decks, handling user input, switching turns,
 * and managing game over scenarios.
 * </p>
 */
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

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Sets up player decks, profile information, and updates the initial UI state.
     */
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

    /**
     * Initializes all FXML elements and sets up the initial game state.
     * <p>
     * This method retrieves player data, sets the user's profile image and name,
     * applies generic button events, disables the UNO button by default,
     * and renders the initial state of the card on the desk and both players' decks.
     * </p>
     */
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

    /**
     * Renders the first player's deck on screen.
     */
    public void renderPlayerOneDeck() {
        this.deckOfPlayerOne.getChildren().clear();
        for (Card card : this.playerOne.getDeck()) {
            Button cardButton = this.createCardButton(card);
            this.deckOfPlayerOne.getChildren().add(cardButton);
        }
    }

    /**
     * Renders the second player's (AI's) deck on screen.
     * The back of the cards is displayed for secrecy.
     */
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

    /**
     * Updates the image view to show the current card on the desk.
     */
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

    /**
     * Updates the UNO button visibility based on player conditions.
     */
    public void renderUnoButton() {
        setDisableUnoButton(!playerOne.hasOneCard() && !playerTwo.hasOneCard());
    }

    /**
     * Enables or disables the UNO button.
     *
     * @param disable whether the button should be disabled
     */
    public void setDisableUnoButton(boolean disable) {
        this.unoButton.setVisible(!disable);
        this.unoButton.setDisable(disable);
    }

    /**
     * Called when the player clicks a card. If the move is valid,
     * the game state and UI are updated accordingly.
     *
     * @param card the card that was played
     */
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

    /**
     * Called when the player clicks a card. If the move is valid,
     * the game state and UI are updated accordingly.
     *
     * @param card the card that was played
     */

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

    /**
     * Handles the event when the player clicks the robber button to draw a card.
     */
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

    /**
     * Called when the player's turn starts. Updates UI and checks available moves.
     */
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

    /**
     * Called when the machine's turn starts. Updates the UI accordingly.
     */
    public void onPlayer2TurnStart() {

        SceneManager.showTurnText("Turno de la Maquina");
    }

    /**
     * Handles the event when the player presses the UNO button.
     */
    @FXML
    private void handleUnoButton() {
        playerOne.singUno(true);
        this.setDisableUnoButton(true);
    }

    /**
     * Navigates back to the setup scene when the back button is pressed.
     *
     * @throws IOException if the setup scene cannot be loaded
     */
    @FXML
    private void handleBackButton() throws IOException {
        GameManager.getGameMaster().stopAllThreads();
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

    /**
     * Handles the confirmation of the game over dialog.
     * Cleans up game data and navigates to the setup scene.
     *
     * @throws IOException if the scene cannot be loaded
     */
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

    /**
     * Displays the game over message and shows a dialog.
     * Also defines a custom behavior when the window is closed after game over.
     *
     * @param message the message to be displayed in the game over dialog
     */
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
