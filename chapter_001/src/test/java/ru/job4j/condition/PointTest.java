package ru.job4j.condition;

import org.junit.Test;

import static java.lang.StrictMath.sqrt;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void whenZeroAndTenThenTen() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 10);
        double result = first.distance(second);
        first.info();
        second.info();
        System.out.println(String.format("Result is %s", result));
        assertThat(result, is(10D));
    }

    @Test
    public void whenCheckItself() {
        Point point = new Point(0, 0);
        double result = point.distance(point);
        assertThat(result, is(0D));
    }

    @Test
    public void whenShowInfo() {
        Point first = new Point(1, 1);
        first.info();
        Point second = new Point(2, 2);
        second.info();
    }

    @Test
    public void distance3dTest1() {
        Point first = new Point(1, 1, 1);
        Point second = new Point(2, 2, 2);
        double result = first.distance3d(second);
        assertThat(result, closeTo(sqrt(3), 0.1));
    }

    @Test
    public void distance3dTest2() {
        Point first = new Point(1, 1, 1);
        Point second = new Point(3, 4, 5);
        double result = first.distance3d(second);
        assertThat(result, closeTo(sqrt(29), 0.1));
    }
}