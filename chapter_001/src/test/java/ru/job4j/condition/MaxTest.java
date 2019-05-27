package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void max2ArgsTest1() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void max2ArgsTest2() {
        Max max = new Max();
        int result = max.max(3, 2);
        assertThat(result, is(3));
    }

    @Test
    public void max2ArgsTest3() {
        Max max = new Max();
        int result = max.max(2, 2);
        assertThat(result, is(2));
    }

    @Test
    public void max3ArgsTest1() {
        Max max = new Max();
        int result = max.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void max3ArgsTest2() {
        Max max = new Max();
        int result = max.max(4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void max3ArgsTest3() {
        Max max = new Max();
        int result = max.max(4, 4, 4);
        assertThat(result, is(4));
    }

    @Test
    public void max4ArgsTest1() {
        Max max = new Max();
        int result = max.max(1, 2, 3, 4);
        assertThat(result, is(4));
    }

    @Test
    public void max4ArgsTest2() {
        Max max = new Max();
        int result = max.max(5, 2, 3, 4);
        assertThat(result, is(5));
    }

    @Test
    public void max4ArgsTest3() {
        Max max = new Max();
        int result = max.max(4, 4, 4, 4);
        assertThat(result, is(4));
    }
}