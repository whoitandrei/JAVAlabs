package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.SqrtCommand;
import src.context.Context;
import src.exceptions.CommandException;

class SqrtCommandTest {
    @Test
    void testSqrt() throws CommandException {
        Context context = new Context();
        context.getStack().push(16.);

        SqrtCommand sqrtCommand = new SqrtCommand();
        sqrtCommand.execute(context, new String[]{});

        assertEquals("4.0", String.valueOf(context.getStack().peek()), "Квадратный корень из 16 должен быть 4");
    }
}
