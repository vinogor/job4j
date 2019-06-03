package ru.job4j.tracker.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    
    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }
    
    @After
    public void loadSys() {
        System.setOut(this.out);
    }
    
    @Test
    public void whenInvalidInput1() {
        String[] values = {"invalid", "7"};
        ValidateInput input = new ValidateInput(
            new StubInput(values)
        );
        input.ask("Enter: ", new int[]{1, 2, 3, 4, 5, 6, 7});
        String nextLine = System.lineSeparator();
        assertThat(
            this.mem.toString(),
            is(
                new StringBuilder()
                    .append("Enter: invalid").append(nextLine)
                    .append("Please enter validate data again.").append(nextLine)
                    .append("Enter: ").append(values[1]).append(nextLine)
                    .toString()
            )
        );
    }
    
    @Test
    public void whenInvalidInput2() {
        String[] values = {"-111", "7"};
        ValidateInput input = new ValidateInput(
            new StubInput(values)
        );
        input.ask("Enter: ", new int[]{1, 2, 3, 4, 5, 6, 7});
        String nextLine = System.lineSeparator();
        assertThat(
            this.mem.toString(),
            is(
                new StringBuilder()
                    .append("Enter: -111").append(nextLine)
                    .append("Please select key from menu").append(nextLine)
                    .append("Enter: ").append(values[1]).append(nextLine)
                    .toString()
            )
        );
    }
}