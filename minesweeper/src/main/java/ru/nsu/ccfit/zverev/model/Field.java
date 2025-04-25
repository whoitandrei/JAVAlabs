package ru.nsu.ccfit.zverev.model;

import java.util.Random;
import ru.nsu.ccfit.zverev.controller.CellState;

public class Field {
    private final int HEIGHT;
    private final int WIDTH;
    private final float DIFFICULTY;
    private Cell[][] field;
    private final int MINES_COUNT;
    private Random random = new Random();

    public final int HEIGHT_LIMIT = 23;
    public final int WIDTH_LIMIT = 46;

    public Field(int HEIGHT, int WIDTH, float DIFFICULTY) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.field = new Cell[HEIGHT][WIDTH];
        this.DIFFICULTY = DIFFICULTY;
        this.MINES_COUNT = (int) (WIDTH * HEIGHT * DIFFICULTY);
        initField();
    }

    private void initField() {
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                field[i][j] = new Cell(false, false, (short) 0);
            }
        }
    }

    public boolean isValidSize(int h, int w) {
        return (h <= HEIGHT_LIMIT && w <= WIDTH_LIMIT);
    }

    public void initFieldMinesCount () {
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                field[i][j].setMinesCount(countMinesAroundCell(j, i));
            }
        }
    }

    public short countMinesAroundCell(int x, int y) {
        short countMines = 0;

        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (isValidCell(nx, ny) && field[ny][nx].isMine()) {
                    countMines++;
                }
            }
        }

        return countMines;
    }

    public void placeMines(int col, int row) {
        int minesPlaced = 0;

        while (minesPlaced < MINES_COUNT) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);

            if (!field[y][x].isMine() && (x != col && y != row)) {
                field[y][x].setMine();
                minesPlaced++;
            }
        }
    }

    public boolean isValidCell (int x, int y) {
         return (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT);
    }

    public CellState getCellState (int x, int y) {
        Cell cell = field[y][x];

        if (!isValidCell(x, y)) throw new IllegalArgumentException("This coordinates are out of bounds: " + x + " " + y);

        if (cell.isOpened()) return  CellState.OPENED;
        if (cell.isFlagged()) return CellState.FLAGGED;
        if (cell.isMine()) return CellState.IS_MINE;

        return CellState.CLOSED;
    }

    public Cell getCell (int x, int y) { return field[y][x]; }
    public int getHEIGHT () { return HEIGHT; }
    public int getWIDTH () { return WIDTH; }
    public int getMINES_COUNT() { return MINES_COUNT; }
    public float getDIFFICULTY() { return DIFFICULTY; }
}
