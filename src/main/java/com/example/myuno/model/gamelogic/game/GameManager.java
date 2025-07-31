package com.example.myuno.model.gamelogic.game;

/**
 * Manages global access to the current game and user information for the UNO application.
 * <p>
 * This utility class provides static methods to retrieve and update the active {@link GameMaster}
 * instance and the username of the current player session.
 * </p>
 */
public class GameManager {

    private static GameMaster currentGame;
    private static String currentUser = "invitado";

    /**
     * Retrieves the currently active game.
     *
     * @return the {@link GameMaster} instance in memory
     */
    public static GameMaster getGameMaster() {
        return currentGame;
    }

    /**
     * Sets the active game instance.
     * If the provided {@code gameMaster} is null, a warning is printed and the assignment is ignored.
     *
     * @param gameMaster the new game instance to assign
     */
    public static void setGameMaster(GameMaster gameMaster) {
        if (gameMaster != null) {
            currentGame = gameMaster;
        } else {
            System.out.println("parce, no se puede un juego nulo, pilas");
        }
    }

    /**
     * Gets the username of the current player.
     *
     * @return the name of the current user
     */
    public static String getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the username of the current player session.
     *
     * @param currentUser the username to assign
     */
    public static void setCurrentUser(String currentUser) {
        GameManager.currentUser = currentUser;
    }
}

