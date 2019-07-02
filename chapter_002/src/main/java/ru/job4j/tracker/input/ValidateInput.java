package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.function.Consumer;

public class ValidateInput implements Input {
    
    private final Input input;
    private final Consumer<String> output;
    
    public ValidateInput(Input input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }
    
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }
    
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException e) {
                output.accept("Please select key from menu");
            } catch (NumberFormatException e) {
                output.accept("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}