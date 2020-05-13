package LMG;// Дан массив целых чисел.
// Найдите сумму чисел, которые в m подряд идущих числах повторяются не менее n раз.
// Каждое число, удовлетворяющее условию, должно учитываться только один раз.
//
//На входе:
//
//numbers - массив целых чисел длиной не менее m
//m - величина диапазона для поиска повторяющихся значений, m больше или равен 2
//n - количество повторов в заданном диапазоне (m), n больше или равен 2
// На выходе: сумма уникальных чисел, удовлетворяющих условию
//
//Пример:
//
//numbers = [1, 3, 5, 6, 5, 7, 5, 3, 3]
//m = 3
//n = 2
//sum_of_unique(numbers, m, n) -->  8 // 5 + 3
//Интервалы по m чисел:  [1, 3, 5], [3, 5, 6], [5, 6, 5], [6, 5, 7], [5, 7, 5], [7, 5, 3], [5, 3, 3]
//
//5 повторяется n раз в интервалах [5, 6, 5] и [5, 7, 5],  по условию 5 должно учитываться только один раз
//3 повторяется n раз в интервале [5, 3, 3]

import java.util.*;

public class Solution2 {

    // Пройдено: 6/8
    // Провалено: 2/8

    public static int sumOfUnique(List<Integer> numbers, int m, int n) {
        // Напишите ваш код здесь...

        if (n > m) {
            return 0;
        }

        if (m > numbers.size()) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);
        map.put(7, 0);
        map.put(8, 0);
        map.put(9, 0);
        map.put(-1, 0);
        map.put(-2, 0);
        map.put(-3, 0);
        map.put(-4, 0);
        map.put(-5, 0);
        map.put(-6, 0);
        map.put(-7, 0);
        map.put(-8, 0);
        map.put(-9, 0);

        Set<Integer> set = new HashSet<>();
        // [1, 3, 5, 6, 5, 7, 5, 3, 3]
        for (int i = 0; i < numbers.size() - m + 1; i++) {
            for (int j = 0; j < m; j++) {
                Integer num = numbers.get(i + j);
                map.put(num, map.get(num) + 1);
            }
            for (Map.Entry entry : map.entrySet()) {
                if ((int) entry.getValue() >= n) {
                    set.add((int) entry.getKey());
                }
            }
            map.put(0, 0);
            map.put(1, 0);
            map.put(2, 0);
            map.put(3, 0);
            map.put(4, 0);
            map.put(5, 0);
            map.put(6, 0);
            map.put(7, 0);
            map.put(8, 0);
            map.put(9, 0);
            map.put(-1, 0);
            map.put(-2, 0);
            map.put(-3, 0);
            map.put(-4, 0);
            map.put(-5, 0);
            map.put(-6, 0);
            map.put(-7, 0);
            map.put(-8, 0);
            map.put(-9, 0);
        }
//        System.out.println(set);
        return set.stream().reduce(Integer::sum).orElse(0);
    }

    public static void runCode() {
        // Данный метод будет вызван из метода main
        // Вы можете писать здесь произвольный код для дебага вашего кода
    }

    public static void main(String[] args) {
        // [1, 3, 5, 6, 5, 7, 5, 3, 3]

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(5);
        list.add(7);
        list.add(5);
        list.add(3);
        list.add(3);

        System.out.println(sumOfUnique(list, 3, 2));
    }

}
