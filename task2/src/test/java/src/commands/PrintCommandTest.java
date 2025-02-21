package src.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import src.commands.PrintCommand;
import src.context.Context;
import src.exceptions.CommandException;

class PrintCommandTest {
    @Test
    void testPrint() throws CommandException {
        Context context = new Context();
        context.getStack().push(42.0);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); // Перехватываем System.out

        PrintCommand printCommand = new PrintCommand();
        printCommand.execute(context, new String[]{});

        String output = outContent.toString().trim(); // Получаем результат из буфера
        Assertions.assertTrue(output.endsWith("42.0"), "PRINT должен вывести 42.0");

        System.setOut(System.out); // Восстанавливаем стандартный вывод
    }

}
