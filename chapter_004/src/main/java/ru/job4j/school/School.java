package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    public Map<String, Student> mapSurnameStudents(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getSurname, o -> o));
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .flatMap(Stream::ofNullable)
                .sorted((o1, o2) -> o2.getScore() - o1.getScore()) // по УБЫВАНИЮ
                .takeWhile(student -> student.getScore() > bound)
                .collect(Collectors.toList());
    }
}