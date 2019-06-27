package ru.job4j.compare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    @Test
    public void sort() {
        SortUser sortUser = new SortUser();

        List<User> list = new ArrayList<>();

        list.add(new User("Sasha", 29));
        list.add(new User("Petya", 300));
        list.add(new User("Kolya", 1));

        Set<User> resultSet = sortUser.sort(list); //
        List<User> resultList = new ArrayList<>(resultSet);
        int result = resultList.get(resultList.size()-1).getAge();

        assertThat(result, is(300));
    }
}