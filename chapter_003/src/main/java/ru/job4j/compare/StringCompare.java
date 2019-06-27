package ru.job4j.compare;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {

        int result = 0;
        int lengthLeft = left.length();
        int lengthRight = right.length();

        int minLen = lengthLeft < lengthRight ? lengthLeft : lengthRight;

        for (int i = 0; i < minLen; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }

        if (result == 0) {
            result = Integer.compare(lengthLeft, right.length());
        }

        return result;
    }
}