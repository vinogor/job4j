package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FuncInterface {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>(end - start);
        for (double i = start; i < end; i++) {
            result.add(func.apply(i));
        }
        return result;
    }
}