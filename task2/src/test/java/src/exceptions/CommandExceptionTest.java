package src.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandExceptionTest {

    @Test
    void testExceptionWithMessage() {
        CommandException exception = new CommandException("Ошибка команды");
        assertEquals("Ошибка команды", exception.getMessage(), "Сообщение исключения должно совпадать");
    }

    @Test
    void testExceptionWithMessageAndCause() {
        Throwable cause = new RuntimeException("Причина ошибки");
        CommandException exception = new CommandException("Ошибка команды", cause);

        assertEquals("Ошибка команды", exception.getMessage(), "Сообщение исключения должно совпадать");
        assertEquals(cause, exception.getCause(), "Причина исключения должна совпадать");
    }
}
