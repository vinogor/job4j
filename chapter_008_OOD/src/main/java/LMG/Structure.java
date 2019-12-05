package LMG;

import java.util.*;

public class Structure {

    private String baseStr =
        "Мой дядя самых честных правил,\n" +
            "Когда не в шутку занемог,\n" +
            "Он уважать себя заставил\n" +
            "И лучше выдумать не мог.\n" +
            "Его пример другим наука;\n" +
            "Но, боже мой, какая скука\n" +
            "С больным сидеть и день и ночь,\n" +
            "Не отходя ни шагу прочь!\n" +
            "Какое низкое коварство\n" +
            "Полуживого забавлять,\n" +
            "Ему подушки поправлять,\n" +
            "Печально подносить лекарство,\n" +
            "Вздыхать и думать про себя:\n" +
            "Когда же черт возьмет тебя";

    public static void main(String[] args) {
        System.out.println(new Structure().start("Мой дядя мог думать про тебя и день и ночь"));
        System.out.println(new Structure().start("Мой дядя мог думать про Linux и Java день и ночь"));
        System.out.println(new Structure().start("123"));
        System.out.println(new Structure().start("Мой Мой"));
        System.out.println(new Structure().start("и и и"));
        System.out.println(new Structure().start("и и и и"));
    }

    private boolean start(String input) {

        // получим массив слов, вырезав знаки припенания и переносы строк
        String[] words = baseStr
            .replaceAll("\n", " ")
            .replaceAll("\\p{Punct}", "")
            .split(" ");

        // считаем кол-во каждого слова и добавляем из в Map
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String str : words) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        // вывод словаря и кол-ва символов в порядке добавления
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }

        String[] inputWords = input.split(" ");

        // проверяем вхождения
        for (String str : inputWords) {
            if (map.containsKey(str)) {
                if (map.get(str) == 0) {
                    return false;
                } else {
                    map.put(str, map.get(str) - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}