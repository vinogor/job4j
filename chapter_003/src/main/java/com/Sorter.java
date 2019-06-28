package com;


import ru.job4j.compare.User;

import java.util.*;

// не импортирован класс User

public class Sorter {

    // можно было не писать, он и так неявно создаётся
    public Sorter(){

    }

    // модификатор доступа public
    Set<User> sort (List<User> list) {

// вместо 3х строк ниже можно было записать всё в одну строку, отправив list в конструктор  TreeSet
// и не создавая новых переменных вернуть всё это:
// return new TreeSet<>(list);
        TreeSet<User> sortedList = new TreeSet<>();
        sortedList.addAll(list);
        return sortedList;
    }

    // кэмэл кейс не выполнен
    // модификатор доступа public

    List<User> sortnamelength (List<User> list) {
        Comparator<User> compar = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compar);
        return list;
    }

    // кэмэл кейс не выполнен
    // модификатор доступа public

    List<User> sortboth (List<User> list) {
        Comparator<User> compar1 = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compar2 = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compar1.thenComparing(compar2));
        return list;
    }
}