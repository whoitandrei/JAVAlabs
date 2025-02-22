package src.calculator;

import java.io.*;
import java.util.Scanner;
import java.util.logging.*;
import src.command_factory.CommandFactory;
import src.commands.Command;
import src.context.Context;
import src.exceptions.*;

public class StackCalculator {
    private static final Logger logger = Logger.getLogger(StackCalculator.class.getName());

    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            for (Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }

            new FileWriter("log.txt", false).close();

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getLevel() + ": " + record.getMessage() + "\n";
                }
            });

            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("error in logging setting: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        CommandFactory factory = new CommandFactory();

        if (args.length > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                processInput(reader, context, factory);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "reading file error", e);
            }
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                processInput(scanner, context, factory);
            }
        }
    }

    private static void processInput(BufferedReader reader, Context context, CommandFactory factory) {
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                executeCommand(line, context, factory);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "input error", e);
        }
    }

    private static void processInput(Scanner scanner, Context context, CommandFactory factory) {
        while (scanner.hasNextLine()) {
            executeCommand(scanner.nextLine(), context, factory);
        }
    }

    private static void executeCommand(String line, Context context, CommandFactory factory) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
            return;
        }

        String[] parts = line.split(" ");
        String commandName = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);

        try {
            Command command = factory.createCommand(commandName);
            command.execute(context, args);
        } catch (CommandException e) {
            logger.log(Level.WARNING, "execution command error: " + line);
            logger.log(Level.FINE, "details of error", e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "critical error: " + line);
            logger.log(Level.FINE, "details of critical error", e);
        }
    }
}
