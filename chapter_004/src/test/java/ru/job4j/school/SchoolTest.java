package ru.job4j.school;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.school.School;
import ru.job4j.school.Student;

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
        students = List.of(
                new Student(99, "Sasha"),
                new Student(70, "Masha"),
                new Student(66, "Katya"),
                new Student(69, "Misha"),
                new Student(12, "Kolya"),
                new Student(48, "Nikita")
        );
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
}