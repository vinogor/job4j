package ru.job4j.array;

/**
 * Array bubble sort.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class BubbleSort {

    /**
     * Sorting.
     *
     * @param array input array.
     * @return sorted array.
     */
    public int[] sort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length - i + 1; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }
}
