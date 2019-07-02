package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SchoolTest {

    private School school;
    private List<Student> students;

    @Before
    public void setUp() {
        school = new School();
        students = List.of(
                new Student(99),
                new Student(70),
                new Student(66),
                new Student(69),
                new Student(12),
                new Student(48)
        );
    }

    @Test
    public void collectTest1() {
        List<Student> result = school.collect(students, student -> student.score < 50);
        assertThat(result, is(Arrays.asList(
                new Student(12),
                new Student(48)
        )));
    }

    @Test
    public void collectTest2() {
        List<Student> result = school.collect(students, student -> student.score >= 50);
        result = school.collect(result, student -> student.score < 70);

        assertThat(result, is(Arrays.asList(
                new Student(66),
                new Student(69)
        )));
    }

    @Test
    public void collectTest3() {
        List<Student> result = school.collect(students, student -> student.score >= 70);
        assertThat(result, is(Arrays.asList(
                new Student(99),
                new Student(70)
        )));
    }
}