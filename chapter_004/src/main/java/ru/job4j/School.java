package ru.job4j;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(99),
                new Student(70),
                new Student(66),
                new Student(69),
                new Student(12),
                new Student(48)
        );
        School school = new School();
        List<Student> collect = school.collect(students, student -> student.score < 50);
        collect.forEach(System.out::println);
    }
}