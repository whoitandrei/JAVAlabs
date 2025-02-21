package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.MulCommand;
import src.context.Context;
import src.exceptions.CommandException;

class MulCommandTest {
    @Test
    void testMul() throws CommandException {
        Context context = new Context();
        context.getStack().push(2.);
        context.getStack().push(4.);

        MulCommand mulCommand = new MulCommand();
        mulCommand.execute(context, new String[]{});

        assertEquals("8.0", String.valueOf(context.getStack().peek()), "2 * 4 должно дать 8");
    }
}
