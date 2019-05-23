package ru.job4j.array;

/**
 * The word starts with.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class ArrayChar {

    /**
     * Checks that the word starts with the prefix.
     *
     * @param prefix prefix.
     * @return if the word starts with the prefix.
     */
    public boolean startsWith(String word, String prefix) {
        char[] pref = prefix.toCharArray();
        char[] wrd = word.toCharArray();

        for (int i = 0; i < pref.length; i++) {
            if (pref[i] != wrd[i]) {
                return false;
            }
        }
        return true;
    }
}