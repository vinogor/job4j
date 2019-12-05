package LMG;

// Написать программу, которая выдает массив чисел,
// в каждом индексе у которого будет произведение всех элементов входного массива
// кроме того, который в текущем индексе входного массива.
// Ограничения: не использовать деление, желательно найти алгоритм, сложность которого O(n)

import java.util.Arrays;

public class Algorithms {

    public static void main(String[] args) {

//        int[] input = {1, 2, 3, 4, 5, 6};
//        int[] input = {10, 10, 10};
//        int[] input = {1, 2, 2, 2, 2, 2};
        int[] input = {1, 2, 3, 4, 5, 6};

        int[] result1 = new Algorithms().multipleSimple(input);
        System.out.println(Arrays.toString(result1));

        int[] result2 = new Algorithms().multipleHard(input);
        System.out.println(Arrays.toString(result2));
    }

    // сложный вариант без деления
    private int[] multipleHard(int[] input) {

        //    a b c d e f - исходный массив, 6 чисел

        // index   0           1           2         3        4         5

        // input   a           b           c         d        e         f
        // tmp1    1           a           a*b       a*b*c    a*b*c*d   a*b*c*d*e
        // tmp2    b*c*d*e*f   c*d*e*f     d*e*f     e*f      f         1

        int len = input.length;
        int[] arrTemp1 = new int[len];
        arrTemp1[0] = 1;

        int[] arrTemp2 = new int[len];
        arrTemp2[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            arrTemp1[i] = arrTemp1[i - 1] * input[i - 1];
            arrTemp2[len - i - 1] = arrTemp2[len - i] * input[len - i];
        }

        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = arrTemp1[i] * arrTemp2[i];
        }

        return result;
    }

    // с делением, простой вариант
    private int[] multipleSimple(int[] input) {

        int len = input.length;
        int[] result = new int[len];
        int mult = 1;

        for (int value : input) {
            mult = mult * value;
        }

        for (int i = 0; i < len; i++) {
            result[i] = mult / input[i];
        }
        return result;
    }
}
