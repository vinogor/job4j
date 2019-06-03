package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

public class ValidateInput implements Input {
    
    private final Input input;
    
    public ValidateInput(Input input) {
        this.input = input;
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
                System.out.println("Please select key from menu");
            } catch (NumberFormatException e) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}