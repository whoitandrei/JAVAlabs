package ru.nsu.ccfit.zverev.tetris.model;


import ru.nsu.ccfit.zverev.tetris.utils.Constants;

/**
 * Этот класс полностью следит за логикой игры
 */

public class GameModel {
    private final Board board;
    private Tetromino currentPiece;
    private Tetromino nextPiece;
    private int score;
    private int level;
    private int posX, posY;

    public GameModel() {
        board = new Board();
        score = 0;
        level = 1;
        spawnNewPiece();
    }

    private void spawnNewPiece() {
        currentPiece = (nextPiece == null) ? Tetromino.getRandom() : nextPiece;
        nextPiece = Tetromino.getRandom();
        posX = Constants.BOARD_WIDTH / 2 - 1;
        posY = -2;

        if (!board.isValidPlace(currentPiece, posX, posY)) {
            // Игра окончена
        }
    }

    public void movePiece(int deltaX, int deltaY) {
        if (board.isValidPlace(currentPiece, posX + deltaX, posY + deltaY)) {
            posX += deltaX;
            posY += deltaY;
        } else if (deltaY != 0) {
            lockPiece();
        }
    }

    private void lockPiece() {
        board.placePiece(currentPiece, posX, posY);
        int lines = board.clearLines();
        updateScore(lines);
        spawnNewPiece();
    }

    private void updateScore(int lines) {
        score += lines * 100 * level;
        level = 1 + score / 1000;
    }



    // getters
    public Board getBoard() { return board; }
    public Tetromino getCurrentPiece() { return currentPiece; }
    public Tetromino getNextPiece() { return nextPiece; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }

}
