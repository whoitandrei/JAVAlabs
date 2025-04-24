package ru.nsu.ccfit.zverev.view.terminal;

import ru.nsu.ccfit.zverev.controller.*;
import java.util.Scanner;


public class TerminalView {
    private GameController controller;

    public TerminalView (GameController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        System.out.println("Welcome to Minesweeper game! \n\n1. Start Game with current settings\n2. Change settings and start the game\n" +
                "3. Scoreboard\n4. About\n5. Exit");
        Scanner in = new Scanner(System.in);

        switch (in.nextInt()) {
            case 1:
                start(); break;
            case 2:
                System.out.println("input height, width, difficulty (EASY/MEDIUM/HARD) and nickname");
                int h, w;
                String d;
                h = in.nextInt();
                w = in.nextInt();
                d = in.nextLine();

                float difficulty = 0.15F;
                switch (d) {
                    case "EASY": difficulty = 0.10F; break;
                    case "MEDIUM": difficulty = 0.15F; break;
                    case "HARD": difficulty = 0.20F; break;
                    default:
                        System.out.println("invalid argument for [-d / --difficulty] (EASY / MEDIUM / HARD)");
                        break;
                }

                this.controller = new GameController(h, w, difficulty);
                start();
                break;
            case 3:
                //controller.getScoreboardManager().printTop();
                in.nextLine();
                showMenu();
                break;
            case 4:
                System.out.println("Welcome to minesweeper game!\n\n" +
                        "you have 2 types of UI: terminal and graphical. to run terminal use flag -t or --terminal for .jar file\n" +
                        "other flags:\n-s/--size [H] [W] - set thi size of field\n" +
                        "-d/--difficulty [EASY/MEDIUM/HARD] - set the difficulty of game (count of all mines)\n" +
                        "-h/--help - get help\n\n" +
                        "how to play? \nTERMINAL: insert cell coordinates to open or flag it. type EXIT to exit the game.\n" +
                        "GRAPHICAL: tap left mouse button to open and right to flag cell\n\n" +
                        "good luck! by Andrei Zverev 23206 - 2025\n\ntap return to continue...");
                in.nextLine();
                showMenu();
                break;
            case 5:
                break;
            default:
                System.out.println("unknown operator. try again:"); break;
        }
    }


    public void start() {
        //System.out.printf("starting game with (%d x %d) field and %d mines in %s mode\n", controller.getField().getHEIGHT(), controller.getField().getWIDTH(), controller.getTotalMinesCount(),"TERMINAL");
        Scanner in = new Scanner(System.in);
        //controller.getGameTimer().start();

        while (!controller.isGameOver()) {
            printBoard();

            System.out.println("choose action: \nOPEN <x> <y> | FLAG <x> <y>");
            String input = in.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 3) {
                try {
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);

                    if (parts[0].equalsIgnoreCase("OPEN") && controller.getField().isValidCell(x, y)) {
                        controller.openCell(x, y);
                    }
                    else if (parts[0].equalsIgnoreCase("FLAG") && controller.getField().isValidCell(x, y)) {
                        controller.changeFlag(x, y);
                    }
                    else {
                        System.out.printf("ERROR: invalid coords: %d %d. try again!\n", x, y);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("invalid input. try again");
                }
            }
            else {
                if (parts[0].equalsIgnoreCase("EXIT")) {
                    controller.setGameOver(true);
                }
                else {
                    System.out.printf("ERROR: unknown operation %s. try again!\n", parts[0]);
                }
            }
            if(controller.isGameOver()) {
                showGameOverDialog();
            }
        }
    }

    private void printBoard() {
        System.out.print("\n\\ ");
        for(int x = 0; x < controller.getField().getWIDTH(); ++x) System.out.print(x + " ");
        System.out.println("x");

        for (int y = 0; y < controller.getField().getHEIGHT(); ++y) {
            System.out.print(y + " ");
            for (int x = 0; x < controller.getField().getWIDTH(); ++x) {
                CellState state = controller.getCellState(x, y);

                /*if (controller.getField().getCell(x, y).isMine()) {
                    System.out.print("* ");
                    continue;
                }

                short minesAround = controller.getMinesAround(x, y);
                System.out.print(minesAround + " ");*/

                switch (state) {
                    case OPENED:
                        short minesAround = controller.getMinesAround(x, y);
                        System.out.print(minesAround + " ");
                        break;

                    case CLOSED:
                        System.out.print("# ");
                        break;

                    case FLAGGED:
                        System.out.print("F ");
                        break;

                    case IS_MINE:
                        if (controller.isGameOver()) System.out.print("* ");
                        else System.out.print("# ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println("y");
        //System.out.println(controller.getGameTimer().getFormattedTime() + "\n");
    }

    private void showGameOverDialog() {
        printBoard();
        //controller.getGameTimer().stop();
        //controller.getScoreboardManager().updateScoreboard();

        if(controller.isGameWon()) System.out.println("congrats! you won! :)");
        else System.out.println("not congrats! you lose!");

        //System.out.println("\nyour score: " + controller.getScoreboardManager().getScore());
        System.out.println("play again? (y/n) (or 's' for print scoreboard and restart)");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equalsIgnoreCase("s")) {
            //controller.getScoreboardManager().printTop();
            restartGame();
        }
        if (choice.equalsIgnoreCase("y")) {
            restartGame();
        }
    }

    private void restartGame() {
        int HEIGHT = controller.getField().getHEIGHT();
        int WIDTH = controller.getField().getWIDTH();
        float DIFFICULTY = controller.getField().getDIFFICULTY();

        controller.resetGame(HEIGHT, WIDTH, DIFFICULTY);
        start();
    }
}
