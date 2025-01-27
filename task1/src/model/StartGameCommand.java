package model;

import model.interfaces.ICommand;
import model.interfaces.IGameProcess;
import model.interfaces.IScreenCleaner;

public class StartGameCommand implements ICommand {
    private final IGameProcess gameProcess;
    private final IScreenCleaner screenCleaner;

    public StartGameCommand(IGameProcess gameProcess, IScreenCleaner screenCleaner) {
        this.gameProcess = gameProcess;
        this.screenCleaner = screenCleaner;
    }

    @Override
    public void execute() {
        screenCleaner.clearScreen();
        gameProcess.playGame();
        screenCleaner.clearScreen();
    }
}