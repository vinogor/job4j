package ru.job4j.school;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SchoolTest {

    private School school;
    private List<Student> students;

    @Before
    public void setUp() {
        school = new School();
        students = new ArrayList<>();
        students.add(new Student(99, "Sasha"));
        students.add(new Student(70, "Masha"));
        students.add(new Student(66, "Katya"));
        students.add(new Student(69, "Misha"));
        students.add(new Student(12, "Kolya"));
        students.add(new Student(48, "Nikita"));
    }

    @Test
    public void collectTest1() {
        List<Student> result = school.collect(students, student -> student.getScore() < 50);
        assertThat(result, is(List.of(
                new Student(12, "Kolya"),
                new Student(48, "Nikita")
        )));
    }

    @Test
    public void collectTest2() {
        List<Student> result = school.collect(students, student -> student.getScore() >= 50);
        result = school.collect(result, student -> student.getScore() < 70);

        assertThat(result, is(List.of(
                new Student(66, "Katya"),
                new Student(69, "Misha")
        )));
    }

    @Test
    public void collectTest3() {
        List<Student> result = school.collect(students, student -> student.getScore() >= 70);
        assertThat(result, is(List.of(
                new Student(99, "Sasha"),
                new Student(70, "Masha")
        )));
    }

    @Test
    public void mapSurnameStudentsTest1() {
        Map<String, Student> result = school.mapSurnameStudents(students);
        assertThat(result, is(Map.of(
                "Sasha", new Student(99, "Sasha"),
                "Masha", new Student(70, "Masha"),
                "Katya", new Student(66, "Katya"),
                "Misha", new Student(69, "Misha"),
                "Kolya", new Student(12, "Kolya"),
                "Nikita", new Student(48, "Nikita")
        )));
    }

    @Test
    public void levelOfTest1() {
        List<Student> result = school.levelOf(students, 70);
        assertThat(result, is(List.of(
                new Student(99, "Sasha")
        )));
    }

    @Test
    public void levelOfTest2() {
        students.add(null);
        List<Student> result = school.levelOf(students, 70);
        assertThat(result, is(List.of(
                new Student(99, "Sasha")
        )));
    }
}