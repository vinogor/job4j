package ru.job4j.compare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    @Test
    public void sortTest1() {
        SortUser sortUser = new SortUser();

        List<User> list = new ArrayList<>();

        list.add(new User("Sasha", 29));
        list.add(new User("Petya", 300));
        list.add(new User("Kolya", 1));

        Set<User> resultSet = sortUser.sort(list);
        List<User> resultList = new ArrayList<>(resultSet);
        int result = resultList.get(resultList.size() - 1).getAge();

        assertThat(result, is(300));
    }

    @Test
    public void sortNameLengthTest1() {
        SortUser sortUser = new SortUser();

        List<User> list = new ArrayList<>();

        list.add(new User("Sasha", 25));
        list.add(new User("Petya1122", 30));
        list.add(new User("Sasha", 20));
        list.add(new User("Petya11", 25));

        List<User> resultList = sortUser.sortNameLength(list);
        String resultName = resultList.get(resultList.size() - 1).getName();

        assertThat(resultName, is("Petya1122"));
    }

    @Test
    public void sortByAllFields() {
        SortUser sortUser = new SortUser();

        List<User> list = new ArrayList<>();

        list.add(new User("Sasha", 25));
        list.add(new User("Petya11", 30));
        list.add(new User("Sasha", 20));
        list.add(new User("Petya11", 25));

        List<User> resultList = sortUser.sortByAllFields(list);
        User result = resultList.get(resultList.size() - 1);

        assertThat(result, is(new User("Petya11", 30)));
    }
}