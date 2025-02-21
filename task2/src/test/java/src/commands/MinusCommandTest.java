package src.commands;

import org.junit.jupiter.api.Test;
import src.commands.MinusCommand;
import src.context.Context;
import src.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.*;

class MinusCommandTest {
    @Test
    void testSub() throws CommandException {
        Context context = new Context();
        context.getStack().push(10.);
        context.getStack().push(3.);

        MinusCommand subCommand = new MinusCommand();
        subCommand.execute(context, new String[]{});

        assertEquals(String.valueOf(7.0), String.valueOf(context.getStack().peek()), "10 - 3 должно дать 7");
    }
}
