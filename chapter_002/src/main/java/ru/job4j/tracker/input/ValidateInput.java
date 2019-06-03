package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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