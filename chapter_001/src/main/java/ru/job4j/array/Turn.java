package ru.job4j.array;

/**
 * Array reverse.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class Turn {

    /**
     * Main calculations.
     *
     * @param array input array
     * @return reverse array
     */
    public int[] back(int[] array) {
        int len = array.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = array[i];
            array[i] = array[len - 1 - i];
            array[len - 1 - i] = temp;
        }
        return array;
    }
}