package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static java.util.Map.entry;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {
    @Test
    public void testConvert1() {
        UserConvert userConvert = new UserConvert();

        User user2 = new User(2, "Sasha", "Spb");
        User user1 = new User(1, "Kate", "Msk");
        User user3 = new User(3, "Max", "Minsk");

        List<User> list = List.of(
                user2,
                user1,
                user3
        );

        HashMap<Integer, User> resultMap = userConvert.process(list);

        assertThat(resultMap, is(Map.ofEntries(
                entry(1, user1),
                entry(2, user2),
                entry(3, user3)
        )));
    }
}