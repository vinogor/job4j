package ru.job4j.loop;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterTest {

    Counter counter;

    @Before
    public void setUp() {
        counter = new Counter();
    }

    @Test
    public void add1() {
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }

    @Test
    public void add2() {
        int result = counter.add(1, 1);
        assertThat(result, is(0));
    }
}