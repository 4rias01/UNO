package com.example.myuno.model.gamelogic.game;

public class GameManager {
    private static GameMaster currentGame;
    private static String currentUser = "invitado";

    public static GameMaster getGameMaster() { return currentGame; }
    public static void setGameMaster(GameMaster gameMaster) {
        if (gameMaster != null) {
            currentGame = gameMaster;
        }
        else {
            System.out.println("parce, no se puede un juego nulo, pilas");
        }
    }
    public static String getCurrentUser() { return currentUser; }
    public static void setCurrentUser(String currentUser) { GameManager.currentUser = currentUser; }
}
