package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner sc = new Scanner(System.in);
    
    @Override
    public String ask(String question) {
        System.out.print(question);
        return sc.nextLine();
    }
    
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}