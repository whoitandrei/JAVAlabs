package model;

import model.interfaces.ICommand;
import model.interfaces.IScreenCleaner;

public class ExitCommand implements ICommand {
    private final IScreenCleaner screenCleaner;

    public ExitCommand(IScreenCleaner screenCleaner) {
        this.screenCleaner = screenCleaner;
    }

    @Override
    public void execute() {
        screenCleaner.clearScreen();
        System.exit(0);
    }
}