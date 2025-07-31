package com.example.myuno.model.gamelogic.game;

import com.example.myuno.model.saves.serializable.SerializableFileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameFileHandler {
    private static final SerializableFileHandler handler = new SerializableFileHandler();
    private static final String GAMES_DIR = "games/";

    public static void createNewGame() {
        GameMaster gameMaster = new GameMaster(true);
        GameManager.setGameMaster(gameMaster);
        System.out.println("creando el game");
        String userName = GameManager.getCurrentUser();
        String name = GAMES_DIR + userName + "_game.ser";
        handler.serialize(name, gameMaster);
    }

    public static GameMaster loadGame(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "invitado";
        }

        GameMaster gameMaster;

        new File(GAMES_DIR).mkdirs();
        String path = GAMES_DIR + name + "_game.ser";

        try{
            if (new File(path).exists()){
                gameMaster = (GameMaster) handler.deserialize(path);
                GameManager.setGameMaster(gameMaster);
                return gameMaster;
            }
            gameMaster = new GameMaster(true);
            GameManager.setGameMaster(gameMaster);
            handler.serialize(path, gameMaster);
            return gameMaster;
        } catch ( Exception e ) {
            System.out.println("mi papacho, trasnoche mas que esto no acaba");
            gameMaster = new GameMaster(true);
            GameManager.setGameMaster(gameMaster);
            return gameMaster;
        }
    }

    public static void saveGame() {
        GameMaster gameMaster = GameManager.getGameMaster();
        new File(GAMES_DIR).mkdirs();

        String name = GameManager.getCurrentUser();
        String path = GAMES_DIR+ name + "_game.ser";
        if (new File(path).exists()){
            handler.serialize(path, gameMaster);
        }
        else{
            System.out.println("Mi loco, que pedo con su programa");
        }
    }

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
