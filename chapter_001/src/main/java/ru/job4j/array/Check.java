package ru.job4j.array;

/**
 * Monotonous array test.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class Check {

    /**
     * Main calculations.
     *
     * @param data input array
     * @return boolean result of checking
     */
    public boolean mono(boolean[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[0] != data[i]) {
                return false;
            }
        }
        return true;
    }
}
