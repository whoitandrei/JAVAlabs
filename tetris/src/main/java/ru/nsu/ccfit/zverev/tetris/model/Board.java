package ru.nsu.ccfit.zverev.tetris.model;

import ru.nsu.ccfit.zverev.tetris.utils.Constants;

/**
 * Класс хранит все методы и данные, нужные для отображения поля и изменения его (удаление строк)
 */

public class Board {
    private static final int HEIGHT = Constants.BOARD_HEIGHT;
    private static final int WIDTH = Constants.BOARD_WIDTH;
    private final int[][] grid;

    private boolean isLineFull(int row) {
        for (int cell : grid[row]) {
            if (cell == 0) return false;
        }
        return true;
    }

    private void removeLine(int row) {
        System.arraycopy(grid, 0, grid, 1, row);
        grid[0] = new int[WIDTH];
    }

    public Board() {
        this.grid = new int[HEIGHT][WIDTH];
    }

    public Board(int[][] grid) {
        this.grid = grid;
    }

    public boolean isValidPlace(Tetromino piece, int x, int y) {
        for (int[] block : piece.getShape()) {
            int newX = x + block[0];
            int newY = y + block[1];

            if (newX < 0 || newX >= Constants.BOARD_WIDTH || newY >= Constants.BOARD_HEIGHT) return false;
            if (newY >= 0 && (grid[newY][newX] != 0)) return false;
        }

        return true;
    }

    public void placePiece(Tetromino piece, int x, int y) {
        for (int[] block : piece.getShape()) {
            int newY = y + block[1];
            if (newY >= 0) {
                grid[newY][x + block[0]] = piece.getColorCode();
            }
        }
    }


    public int clearLines () {
        int linesCleared = 0;

        for (int row = Constants.BOARD_HEIGHT - 1; row >= 0; row--) {
            if (isLineFull(row)) {
                removeLine(row);
                linesCleared++;
                row++;
            }
        }

        return linesCleared;
    }



    // getters
    public int[][] getGrid () { return grid; }
}
