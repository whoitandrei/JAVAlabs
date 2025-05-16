package ru.nsu.ccfit.zverev.minesweeper;

import ru.nsu.ccfit.zverev.view.graphical.*;
import ru.nsu.ccfit.zverev.view.terminal.*;
import ru.nsu.ccfit.zverev.controller.*;

import java.util.Scanner;


public class Minesweeper {
    public static void main(String[] args) {
        int height = 5;
        int width = 5;
        boolean terminalMode = false;
        float difficulty = 0.15F;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-h") || args[i].equals("--help")) {
                Scanner in = new Scanner(System.in);
                System.out.println("Welcome to minesweeper game!\n\n" +
                        "you have 2 types of UI: terminal and graphical. to run graphical use flag -g or --graphical for .jar file\n" +
                        "other flags:\n-s/--size [H] [W] - set thi size of field\n" +
                        "-d/--difficulty [EASY/MEDIUM/HARD] - set the difficulty of game (count of all mines)\n" +
                        "-h/--help - get help\n\n" +
                        "how to play? \nTERMINAL: insert cell coordinates to open or flag it. type EXIT to exit the game.\n" +
                        "GRAPHICAL: tap left mouse button to open and right to flag cell\n\n" +
                        "good luck! :^)\n");
                return;
            }
            else if (args[i].equals("-t") || args[i].equals("--terminal")){
                terminalMode = true;
            }
            else if (args[i].equals("-s") || args[i].equals("--size")){
                if (i + 2 < args.length) {
                    try {
                        int HEIGHT = Integer.parseInt(args[i+1]);
                        int WIDTH = Integer.parseInt(args[i+2]);
                        height = Math.max(5, HEIGHT);
                        width = Math.max(5, WIDTH);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("invalid argument for [-s / --size]");
                    }
                }
                else System.out.println("[-s / --size] requires 2 arguments!");
            }
            else if (args[i].equals("-d") || args[i].equals("--difficulty")) {
                if (i + 1 < args.length) {
                    try {
                        switch (args[i + 1]) {
                            case "EASY": difficulty = 0.10F; break;
                            case "MEDIUM": difficulty = 0.15F; break;
                            case "HARD": difficulty = 0.20F; break;
                            default:
                                System.out.println("invalid argument for [-d / --difficulty] (EASY / MEDIUM / HARD)");
                                break;
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("invalid argument for [-d / --difficulty]");
                    }
                }
                else System.out.println("[-d / --difficulty] requires on of (EASY / MEDIUM / HARD) parameters");
            }
            else System.out.println("unknown parameter: " + args[i]);
        }


        System.out.printf("starting game with (%d x %d) field and %d mines in %s mode\n", height, width, (int)(height * width * difficulty), terminalMode ? "TERMINAL" : "GRAPHICAL");
        GameController controller = new GameController(height, width, difficulty);

        if (terminalMode) new TerminalView(controller).showMenu();
        else new GraphicalView(controller);
        //else System.out.println("sorry... graphical option is developing...");
    }
}
