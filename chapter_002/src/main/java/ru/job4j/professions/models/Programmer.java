package ru.job4j.professions.models;

public class Programmer extends Engineer {

    private String[] studiedTechnologies;

    private String currentProject;

    public Programmer(String name, String surname, String education, String birthday, String levelOfAccessToSecrecy, String[] studiedTechnologies, String currentProject) {
        super(name, surname, education, birthday, levelOfAccessToSecrecy);
        this.studiedTechnologies = studiedTechnologies;
        this.currentProject = currentProject;
    }

    public String[] getStudiedTechnologies() {
        return studiedTechnologies;
    }

    public String getCurrentProject() {
        return currentProject;
    }
}
