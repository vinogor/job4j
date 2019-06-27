package ru.job4j.compare;

import java.util.*;

public class SortUser {
    public Set<User> sort (List<User> list) {
        // будет возвращать TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength (List<User> list) {
        // в этом методе необходимо определить Comparator для метода Collections.sort и
        // отсортировать List<User> по длине имени.
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return list;
    }

    public List<User> sortByAllFields (List<User> list) {
        // в этом методе необходимо определить Comparator для метода Collections.sort и
        // отсортировать List<User> по обоим полям, сначала сортировка по имени
        // в лексикографическом порядке, потом по возрасту.
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
               int diff =  o1.getName().length() - o2.getName().length();
               return diff == 0 ? o1.getAge() - o2.getAge() : diff;
            }
        });
        return list;
    }
}