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
        List<User> list = new ArrayList<>();

        User user2 = new User(2, "Sasha", "Spb");
        User user1 = new User(1, "Kate", "Msk");
        User user3 = new User(3, "Max", "Minsk");

        list.add(user2);
        list.add(user1);
        list.add(user3);

        HashMap<Integer, User> resultMap = userConvert.process(list);

        Map<Integer, User> expect = Map.ofEntries(
                entry(1, user1),
                entry(2, user2),
                entry(3, user3)
        );

        assertThat(resultMap, is(expect));
    }
}