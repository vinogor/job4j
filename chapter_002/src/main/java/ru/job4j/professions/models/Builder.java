package ru.job4j.professions.models;

public class Builder extends Engineer {

	public Builder(String name, String surname, String education, String birthday, String levelOfAccessToSecrecy) {
		super(name, surname, education, birthday, levelOfAccessToSecrecy);
	}

	void mixConcrete() {

	}
}
