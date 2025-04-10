package ru.nsu.ccfit.zverev.tetris.utils;

import java.awt.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final int BLOCK_SIZE = 30;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final Color[] COLORS = { /* цвета для фигур */ };

    public static final List<int[][][]> SHAPES = Collections.unmodifiableList(Arrays.asList(
            // I-образная
            new int[][][]{{{0,0}, {1,0}, {2,0}, {3,0}}, {{0,0}, {0,1}, {0,2}, {0,3}}},
            // O-образная
            new int[][][]{{{0,0}, {1,0}, {0,1}, {1,1}}},
            // T-образная
            new int[][][]{{{0,0}, {1,0}, {2,0}, {1,1}}, {{1,0}, {1,1}, {1,2}, {0,1}}, /* ... */}
            // Остальные фигуры
    ));
}
