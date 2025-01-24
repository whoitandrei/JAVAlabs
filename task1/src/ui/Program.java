package ui;
import model.*;

public class Program {
    public static void main(String[] args) {
        BullsAndCowsGame game = new BullsAndCowsGame();
        if (args.length > 0 && args[0].equals("-h")){
            game.displayHelp();
        }
        game.displayMenu();
    }
}
