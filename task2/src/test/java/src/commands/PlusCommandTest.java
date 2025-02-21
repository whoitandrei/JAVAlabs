package src.commands;

import org.junit.jupiter.api.Test;
import src.commands.PlusCommand;
import src.context.Context;
import src.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.*;

class PlusCommandTest {
    @Test
    void testAdd() throws CommandException {
        Context context = new Context();
        context.getStack().push(5.);
        context.getStack().push(10.);

        PlusCommand addCommand = new PlusCommand();
        addCommand.execute(context, new String[]{});

        assertEquals(String.valueOf(15.), String.valueOf(context.getStack().peek()), "Сложение должно дать 15");
    }
}
