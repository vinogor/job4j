package ru.job4j.professions.models;

public class Surgeon extends Doctor {

	private int numberOfOperations;

	public Surgeon(String name, String surname, String education, String birthday, int numberOfOperations) {
		super(name, surname, education, birthday);
		this.numberOfOperations = numberOfOperations;
	}

	public int getNumberOfOperations() {
		return numberOfOperations;
	}

	public void disinfectDevices() {

	}
}