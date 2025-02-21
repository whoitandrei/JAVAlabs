package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.PushCommand;
import src.context.Context;
import src.exceptions.CommandException;

import java.util.Stack;

class PushCommandTest {
    @Test
    void testPush() throws CommandException {
        Context context = new Context();
        PushCommand pushCommand = new PushCommand();

        pushCommand.execute(context, new String[]{"10"});
        assertEquals("10.0", String.valueOf(context.getStack().peek()), "Должно быть 10 в стеке");
    }
}
