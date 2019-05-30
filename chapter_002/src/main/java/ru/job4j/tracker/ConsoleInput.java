package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner sc = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return sc.nextLine();
    }
}