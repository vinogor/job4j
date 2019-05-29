package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput {

	public String ask(String question) {
		System.out.print(question);
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
}