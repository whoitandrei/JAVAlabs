package model;

import model.Commands.ExitCommand;
import model.Commands.ICommand;
import model.menu.IMenuDisplay;

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