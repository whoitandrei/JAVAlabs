package model;

import model.interfaces.ICommand;
import model.interfaces.IScreenCleaner;

import java.util.Scanner;

public class DisplayHelpCommand implements ICommand {
    private final IScreenCleaner screenCleaner;

    public DisplayHelpCommand(IScreenCleaner screenCleaner) {
        this.screenCleaner = screenCleaner;
    }

    @Override
    public void execute() {
        screenCleaner.clearScreen();
        String help = """
                DESCRIPTION:
                Bulls and Cows is a logic-based game where one player thinks of a number (usually a 4-digit number with unique digits), and the other player tries to guess it.
                
                    Bull: A digit is correct and in the right position.
                
                    Cow: A digit is correct but in the wrong position.
                
                The goal is to guess the number in the fewest attempts.
                
                HOW TO PLAY:
                1) type 1 in menu
                2) type your guesses
                3) think about your previous guess and make next guess
                4) if you want to stop the game type 999999 in guess field
                
                GOOD LUCK!!
                By Andrei Zverev 23206
                
                type any message + Enter to exit...
                """;
        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();
        screenCleaner.clearScreen();
    }
}