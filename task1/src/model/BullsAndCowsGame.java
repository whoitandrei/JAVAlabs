package model;
import java.util.Scanner;

public class BullsAndCowsGame {
    public void displayMenu() {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            clearScreen();
            System.out.println("=== Bulls And Cows Menu ===");
            System.out.println("1. Start Game");
            System.out.println("2. Help: Game Description, How To Play, etc.");
            System.out.println("3. Exit");
            System.out.print("Choose Option: ");

            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    clearScreen();
                    displayHelp();
                    break;
                case 3:
                    isRunning = false;
                    clearScreen();
                    break;
            }
        }
        in.close();
    }

    public void displayHelp(){
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
        Scanner in = new Scanner(System.in);
        String n = in.nextLine();
    }

    private void startGame() {
        clearScreen();
        GameProcess process = new GameProcess();
        process.playGame();
        clearScreen();
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error [console cleaning]: " + e.getMessage());
        }
    }
}
