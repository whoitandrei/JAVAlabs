package ru.nsu.ccfit.zverev.model;

public class Cell {
    private boolean isMine;
    private boolean isFlagged;
    private short minesCount;
    private boolean isOpened;

    public Cell(boolean isMine, boolean isFlagged, short minesCount) {
        this.isMine = isMine;
        this.isFlagged = isFlagged;
        this.minesCount = minesCount;
        this.isOpened = false;
    }

    public void changeFlag() { isFlagged = !isFlagged; }
    public void setMine() { isMine = true; }
    public void setMinesCount(short n) { minesCount = n; }
    public void openCell () { isOpened = true; }

    //getters
    public boolean isOpened() { return isOpened; }
    public boolean isMine() { return isMine; }
    public boolean isFlagged() { return isFlagged; }
    public short getMinesCount() { return minesCount; }
}
