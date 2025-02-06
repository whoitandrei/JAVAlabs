package ui;

import model.*;
import model.Commands.DisplayHelpCommand;
import model.Commands.ExitCommand;
import model.Commands.ICommand;
import model.Commands.StartGameCommand;
import model.gameprocess.GameProcess;
import model.gameprocess.IGameProcess;
import model.menu.IMenuDisplay;
import model.screencleaner.IScreenCleaner;
import model.menu.MenuDisplay;
import model.screencleaner.ScreenCleaner;

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