package ru.nsu.ccfit.zverev.model;

import java.io.DataInput;


public class Game {
    Field gameField;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isFieldGenerated;

     public Game(int HEIGHT, int WIDTH, float DIFFICULTY) {
         this.gameField = new Field(HEIGHT, WIDTH, DIFFICULTY);
         this.isGameOver = false;
         this.isGameWon = false;
         this.isFieldGenerated = false;
     }

     private void generateField(int col, int row) {
         gameField.placeMines(col, row);
         gameField.initFieldMinesCount();
         isFieldGenerated = true;
     }

     public void openCell(int x, int y) {
         if (isGameOver) return;
         if (!isFieldGenerated) generateField(x, y);

         Cell cell = gameField.getCell(x, y);
         if (cell.isOpened() || cell.isFlagged()) return;
         if (cell.isMine()){
             isGameOver = true;
             return;
         }

         openEmpty(x, y);
         checkWinCondition();
     }

     private void openEmpty(int x, int y) {
         if (!gameField.isValidCell(x, y)) return;

         Cell cell = gameField.getCell(x, y);
         if (cell.isOpened() || cell.isMine() || cell.isFlagged()) return;
         cell.openCell();

         if (cell.getMinesCount() == 0) {
             for (int dx = -1; dx <= 1; ++dx) {
                 for (int dy = -1; dy <= 1; ++dy) {
                     if (dx == 0 && dy == 0) continue;
                     if (gameField.isValidCell(x + dx, y + dy)) openEmpty(x + dx, y + dy);
                 }
             }
         }
     }

     public void placeFlag (int x, int y) {
         if (isGameOver) return;
         Cell cell = gameField.getCell(x,y);
         if (!cell.isOpened()) {
             cell.changeFlag();
         }
     }

     private void checkWinCondition() {
         for (int y = 0; y < gameField.getHEIGHT(); ++y) {
             for (int x = 0; x < gameField.getWIDTH(); ++x) {
                 Cell cell = gameField.getCell(x, y);
                 if (!cell.isOpened() && !cell.isMine()) return;
             }
         }

         isGameOver = true;
         isGameWon = true;
     }


    public boolean isGameOver() { return isGameOver; }
    public boolean isGameWon() { return isGameWon; }
    public Field getGameField() { return gameField; }
}
