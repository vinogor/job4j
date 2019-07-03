package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    public Map<String, Student> mapSurnameStudents(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getSurname, o -> o));
    }

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(99, "Sasha"),
                new Student(70, "Masha"),
                new Student(66, "Katya"),
                new Student(69, "Misha"),
                new Student(12, "Kolya"),
                new Student(48, "Nikita")
        );
        School school = new School();
        List<Student> collect = school.collect(students, student -> student.score < 50);
        collect.forEach(System.out::println);
    }
}