package ru.job4j.professions.models;

public class Dentist extends Doctor {

    private Patient[] patients;

    public Dentist(String name, String surname, String education, String birthday, Patient[] patients) {
        super(name, surname, education, birthday);
        this.patients = patients;
    }

    public int countNumberOfTeeth(Patient patient) {
        return -1;
    }

    public Patient[] getPatients() {
        return patients;
    }
}