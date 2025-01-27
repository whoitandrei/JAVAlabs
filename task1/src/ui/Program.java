package ui;

import model.*;
import model.interfaces.ICommand;
import model.interfaces.IGameProcess;
import model.interfaces.IMenuDisplay;
import model.interfaces.IScreenCleaner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IScreenCleaner screenCleaner = new ScreenCleaner();
        IGameProcess gameProcess = new GameProcess();

        Map<Integer, ICommand> commands = new HashMap<>();
        commands.put(1, new StartGameCommand(gameProcess, screenCleaner));
        commands.put(2, new DisplayHelpCommand(screenCleaner));
        commands.put(3, new ExitCommand(screenCleaner));

        IMenuDisplay menuDisplay = new MenuDisplay(screenCleaner, scanner, commands);
        BullsAndCowsGame game = new BullsAndCowsGame(menuDisplay);

        game.run();
    }
}