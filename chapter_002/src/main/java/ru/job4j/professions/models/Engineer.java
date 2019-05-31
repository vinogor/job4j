package ru.job4j.professions.models;

public class Engineer extends Profession {
    private String levelOfAccessToSecrecy;

    public Engineer(String name, String surname, String education, String birthday, String levelOfAccessToSecrecy) {
        super(name, surname, education, birthday);
        this.levelOfAccessToSecrecy = levelOfAccessToSecrecy;
    }

    public void goOnBusinessTrip(int days, String location) {

    }

    public String getLevelOfAccessToSecrecy() {
        return levelOfAccessToSecrecy;
    }
}