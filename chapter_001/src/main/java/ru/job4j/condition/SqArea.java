package ru.job4j.condition;

public class SqArea {
    public int square(int p, int k) {
        int h = (p * k) / (2 * (1 + k));
        return h * h / k;
    }
}