package model.menu;

import model.Commands.ICommand;
import model.screencleaner.IScreenCleaner;

import java.util.Map;
import java.util.Scanner;

public class MenuDisplay implements IMenuDisplay {
    private final Scanner in;
    private final Map<Integer, ICommand> commands;
    private final IScreenCleaner screenCleaner;

    public MenuDisplay(IScreenCleaner screenCleaner, Scanner in, Map<Integer, ICommand> commands) {
        this.in = in;
        this.commands = commands;
        this.screenCleaner = screenCleaner;
    }

    @Override
    public ICommand displayMenu() {
        int choice = -1;

        do {
            screenCleaner.clearScreen();
            System.out.println("=== Bulls And Cows Menu ===");
            System.out.println("1. Start Game");
            System.out.println("2. Help: Game Description, How To Play, etc.");
            System.out.println("3. Exit");
            System.out.print("Choose Option: ");

            choice = in.nextInt();
            screenCleaner.clearScreen();
        } while (!commands.containsKey(choice));

        return commands.get(choice);
    }
}