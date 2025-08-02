package com.example.myuno.model.gamelogic.game;

import com.example.myuno.exceptions.GameLoadException;
import com.example.myuno.model.saves.serializable.SerializableFileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles the creation, loading, saving, and deletion of UNO game files.
 * <p>
 * This utility class centralizes file-based persistence of {@link GameMaster} instances.
 * It operates using serialized files located in the {@code games/} directory.
 * </p>
 */
public class GameFileHandler {

    private static final SerializableFileHandler handler = new SerializableFileHandler();
    private static final String GAMES_DIR = "games/";

    /**
     * Creates and saves a new game for the current user.
     * Initializes a new {@link GameMaster}, assigns it to the {@link GameManager},
     * and serializes the game to a file based on the user's name.
     */
    public static void createNewGame() {
        GameMaster gameMaster = new GameMaster(true);
        GameManager.setGameMaster(gameMaster);
        String userName = GameManager.getCurrentUser();
        String name = GAMES_DIR + userName + "_game.ser";
        handler.serialize(name, gameMaster);
    }

    /**
     * Loads a saved game for the specified user.
     * If the save file does not exist, a new game is created and saved instead.
     *
     * @param name the username to identify the save file; if null or empty, defaults to "invitado"
     * @return the loaded or newly created {@link GameMaster} instance
     * @throws GameLoadException if the game cannot be loaded due to an I/O or deserialization issue
     */
    public static GameMaster loadGame(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "invitado";
        }

        GameMaster gameMaster;

        new File(GAMES_DIR).mkdirs();
        String path = GAMES_DIR + name + "_game.ser";

        try {
            if (new File(path).exists()) {
                gameMaster = (GameMaster) handler.deserialize(path);
                GameManager.setGameMaster(gameMaster);
                return gameMaster;
            }
            gameMaster = new GameMaster(true);
            GameManager.setGameMaster(gameMaster);
            handler.serialize(path, gameMaster);
            return gameMaster;
        } catch (Exception e) {
            throw new GameLoadException("Error al cargar el juego desde " + path, e);
        }
    }

    /**
     * Saves the current game associated with the current user.
     * If a save file already exists, it will be overwritten.
     * Otherwise, a message is printed to the console.
     */
    public static void saveGame() {
        GameMaster gameMaster = GameManager.getGameMaster();
        new File(GAMES_DIR).mkdirs();

        String name = GameManager.getCurrentUser();
        String path = GAMES_DIR + name + "_game.ser";
        if (new File(path).exists()) {
            handler.serialize(path, gameMaster);
        } else {
            System.out.println("Mi loco, que pedo con su programa");
        }
    }

    /**
     * Deletes the saved game file for the current user, if it exists.
     * If no valid username is provided, defaults to "invitado".
     */
    public static void deleteGameFile() {
        String userName = GameManager.getCurrentUser();
        if (userName == null || userName.trim().isEmpty()) {
            userName = "invitado";
        }
        String path = GAMES_DIR + userName + "_game.ser";
        try {
            Files.deleteIfExists(Paths.get(path));
            System.out.println("Archivo eliminado: " + path);
        } catch (IOException e) {
            System.out.println("No se pudo eliminar el archivo de guardado");
            e.printStackTrace();
        }
    }
}
