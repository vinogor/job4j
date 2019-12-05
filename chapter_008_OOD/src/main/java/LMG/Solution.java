package LMG;

import java.util.Arrays;

import java.util.*;

class Solution {


    public static int fourLetters(String sentence) {
        // Напишите ваш код здесь...
        return (int) Arrays.stream(sentence.split(" ")).filter(s -> s.length() == 4).count();
    }


    public static boolean tickets(List<Integer> people) {
        // Напишите ваш код здесь...


        Map<Integer, Integer> map = new TreeMap<>();

        map.put(50, 0);
        map.put(100, 0);
        map.put(200, 0);

        int bilet = 50;
        for (int money : people) {
            int change = money - bilet;
            map.put(money, map.get(money) + 1);

            if (change > 0) {
                while (change >= 200 && map.get(200) > 0) {
                    change = change - 200;
                    map.put(200, map.get(200) - 1);
                }

                while (change >= 100 && map.get(100) > 0) {
                    change = change - 100;
                    map.put(100, map.get(100) - 1);
                }

                while (change >= 50 && map.get(50) > 0) {
                    change = change - 50;
                    map.put(50, map.get(50) - 1);
                }

                if (change > 0) {
                    return false;
                }

            }
        }
        return true;
    }

    public static void runCode() {
        // Данный метод будет вызван из метода main
        // Вы можете писать здесь произвольный код для дебага вашего кода
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(50);
        list.add(50);
        list.add(100);
        list.add(100);
//        list.add(100);
//        list.add(100);

        list.add(200);


        System.out.println(tickets(list));
    }
}