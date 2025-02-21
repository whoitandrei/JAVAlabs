package src.command_factory;

import src.commands.Command;
import src.exceptions.CommandException;

import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.util.Scanner;

public class CommandFactory {
    private Map<String, String> commandMap = new HashMap<>();

    public CommandFactory() {
        loadCommandMap();
    }

    private void loadCommandMap() {
        try (InputStream inputStream = getClass().getResourceAsStream("/commands.config")) {
            if (inputStream == null) {
                throw new RuntimeException("commands.config not found");
            }
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(" ");
                    commandMap.put(parts[0], parts[1]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load command map", e);
        }
    }

    public Command createCommand(String commandName) throws CommandException {
        String className = commandMap.get(commandName);
        if (className == null) {
            throw new CommandException("Unknown command: " + commandName);
        }

        try {
            Class<?> clazz = Class.forName(className);
            return (Command) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CommandException("Failed to create command: " + commandName, e);
        }
    }
}