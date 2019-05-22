package ru.job4j.loop;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    Factorial factorial;

    @Before
    public void setUp() {
        factorial = new Factorial();
    }

    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }
}