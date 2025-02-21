package src.Context;

import org.junit.jupiter.api.Test;
import src.context.Context;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.Stack;

class ContextTest {

    @Test
    void testStackOperations() {
        Context context = new Context();
        Stack<Double> stack = context.getStack();

        assertTrue(stack.isEmpty(), "Стек должен быть пустым при создании");

        stack.push(10.5);
        stack.push(20.0);

        assertEquals(20.0, stack.pop(), "Последний добавленный элемент должен быть 20.0");
        assertEquals(10.5, stack.pop(), "Следующий элемент должен быть 10.5");

        assertTrue(stack.isEmpty(), "Стек должен быть пустым после удаления всех элементов");
    }

    @Test
    void testDefinesOperations() {
        Context context = new Context();
        Map<String, Double> defines = context.getDefines();

        assertTrue(defines.isEmpty(), "Словарь должен быть пустым при создании");

        defines.put("PI", 3.1415);
        defines.put("E", 2.718);

        assertEquals(3.1415, defines.get("PI"), "PI должно быть 3.1415");
        assertEquals(2.718, defines.get("E"), "E должно быть 2.718");

        assertTrue(defines.containsKey("PI"), "Должен быть ключ 'PI'");
        assertTrue(defines.containsKey("E"), "Должен быть ключ 'E'");
    }
}
