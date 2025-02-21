package src.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class StackCalculatorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testMainWithFile() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("PUSH 5\nPUSH 10\nPRINT\n");
        }

        StackCalculator.main(new String[]{tempFile.getAbsolutePath()});

        String output = outContent.toString().trim();
        Assertions.assertTrue(output.endsWith("10.0"), "Вывод программы должен заканчиваться на 10");
    }
}
