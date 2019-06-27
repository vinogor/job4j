package ru.job4j.compare;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort (List<User> list) {
        // будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
        return new TreeSet<>(list);
    }
}
