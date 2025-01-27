package model;

import model.interfaces.ICommand;
import model.interfaces.IMenuDisplay;

public class BullsAndCowsGame {
    private final IMenuDisplay menuDisplay;

    public BullsAndCowsGame(IMenuDisplay menuDisplay) {
        this.menuDisplay = menuDisplay;
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            ICommand command = menuDisplay.displayMenu();
            command.execute();

            if (command instanceof ExitCommand) {
                isRunning = false;
            }
        }
    }
}