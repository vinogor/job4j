package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FuncInterfaceTest {

    @Test
    public void diapasonTest1() {
        FuncInterface function = new FuncInterface();
        List<Double> result = function.diapason(
                5, 8,
                x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void diapasonTest2() {
        FuncInterface function = new FuncInterface();
        List<Double> result = function.diapason(
                1, 4,
                x -> x * x + 1);
        List<Double> expected = Arrays.asList(2D, 5D, 10D);
        assertThat(result, is(expected));
    }

    @Test
    public void diapasonTest3() {
        FuncInterface function = new FuncInterface();
        List<Double> result = function.diapason(
                5, 8,
                x -> Math.round((100 / Math.log(x)) * 100.0) / 100.0);

        List<Double> expected = Arrays.asList(62.13D, 55.81D, 51.39D);
        assertThat(result, is(expected));

        // cannot resolve method closeTo...
        // assertThat(result, closeTo(expected, 1D));
    }
}