package ru.nsu.ccfit.zverev.controller;

import ru.nsu.ccfit.zverev.model.*;
import ru.nsu.ccfit.zverev.util.*;

public class GameController {
    private Game game;
    private boolean isGameOver;
    private ScoreboardManager scoreboardManager;


    public GameController(int HEIGHT, int WIDTH, float DIFFICULTY) {
        this.game = new Game(HEIGHT, WIDTH, DIFFICULTY);
        this.isGameOver = false;
        this.scoreboardManager = new ScoreboardManager();
    }

    public CellState getCellState(int x, int y) { return game.getGameField().getCellState(x, y); }

    public void openCell (int x, int y) {
        if (isGameOver) return;
        game.openCell(x, y);
        if (game.isGameOver()) isGameOver = true;
    }

    public void changeFlag (int x, int y) {
        if (isGameOver) return;
        game.placeFlag(x, y);
    }

    public void resetGame(int HEIGHT, int WIDTH, float DIFFICULTY) {
        this.game = new Game(HEIGHT, WIDTH, DIFFICULTY);
        this.isGameOver = false;
    }


    public boolean isGameOver() {
        return isGameOver;
    }

    public ScoreboardManager getScoreboardManager() { return scoreboardManager; }
    public void setGameOver(boolean t) { isGameOver = t; }
    public Field getField () { return game.getGameField(); }
    public boolean isGameWon () { return game.isGameWon(); }
    public int getTotalMinesCount () { return game.getGameField().getMINES_COUNT(); }
    public short getMinesAround(int x, int y) { return game.getGameField().getCell(x, y).getMinesCount(); }
}
