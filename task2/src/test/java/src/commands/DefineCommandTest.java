package src.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.commands.DefineCommand;
import src.context.Context;
import src.exceptions.CommandException;


class DefineCommandTest {
    @Test
    void testDefine() throws CommandException {
        Context context = new Context();

        DefineCommand defineCommand = new DefineCommand();
        defineCommand.execute(context, new String[]{"x", "10"});

        assertEquals("10.0", String.valueOf(context.getDefines().get("x")), "Переменная x должна быть равна 10");
    }
}
