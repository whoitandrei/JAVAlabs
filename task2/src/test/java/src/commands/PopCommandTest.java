package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.PopCommand;
import src.context.Context;
import src.exceptions.CommandException;

class PopCommandTest {
    @Test
    void testPop() throws CommandException {
        Context context = new Context();
        context.getStack().push(42.0);

        PopCommand popCommand = new PopCommand();
        popCommand.execute(context, new String[]{});

        assertTrue(context.getStack().isEmpty(), "Стек должен быть пустым после POP");
    }
}
