package model.Commands;

import model.gameprocess.IGameProcess;
import model.screencleaner.IScreenCleaner;

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