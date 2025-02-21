package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.DivCommand;
import src.context.Context;
import src.exceptions.CommandException;

class DivCommandTest {
    @Test
    void testDiv() throws CommandException {
        Context context = new Context();
        context.getStack().push(10.);
        context.getStack().push(2.);

        DivCommand divCommand = new DivCommand();
        divCommand.execute(context, new String[]{});

        assertEquals("5.0", String.valueOf(context.getStack().peek()), "10 / 2 должно дать 5");
    }

    @Test
    void testDivByZero() {
        Context context = new Context();
        context.getStack().push(10.);
        context.getStack().push(0.);

        DivCommand divCommand = new DivCommand();
        assertThrows(CommandException.class, () -> divCommand.execute(context, new String[]{}), "Должно выброситься CommandException при делении на 0");
    }
}
