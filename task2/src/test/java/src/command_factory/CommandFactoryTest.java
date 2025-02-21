package src.command_factory;

import org.junit.jupiter.api.Test;
import src.commands.Command;
import src.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void testCreateValidCommand() throws CommandException {
        CommandFactory factory = new CommandFactory();
        Command command = factory.createCommand("PUSH");
        assertNotNull(command);
    }

    @Test
    void testCreateInvalidCommand() {
        CommandFactory factory = new CommandFactory();
        assertThrows(CommandException.class, () -> factory.createCommand("INVALID"));
    }
}
