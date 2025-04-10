package ru.nsu.ccfit.zverev.tetris.model;

import ru.nsu.ccfit.zverev.tetris.utils.Constants;
import java.util.Random;

/** Класс хранит всевозможные фигуры и их повороты
 */

public class Tetromino {
    private int rotationState;
    private final int colorCode;
    private final int[][][] rotations;

    public enum Type {
        I(0xFF0000), O(0x00FF00), T(0x0000FF), S(0xFFFF00), Z(0xFF00FF), J(0x00FFFF), L(0xFFFFFF);

        public final int colorCode;
        Type(int colorCode) {
            this.colorCode = colorCode;
        }
    }

    public Tetromino(Type type) {
        this.rotations = Constants.SHAPES.get(type.ordinal());
        this.colorCode = type.colorCode;
        this.rotationState = 0;
    }

    public void rotate(boolean clockwise) {
        rotationState = (rotationState + (clockwise ? 1 : -1) + rotations.length) % rotations.length;
    }



    // getters
    public int[][] getShape() { return rotations[rotationState]; }
    public int getColorCode() { return colorCode; }
    public static Tetromino getRandom() {
        return new Tetromino(Type.values()[new Random().nextInt(Type.values().length)]);
    }

}
