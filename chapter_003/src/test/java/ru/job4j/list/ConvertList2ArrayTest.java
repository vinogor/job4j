package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when5ElementsThen6() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5),
                3
        );
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void test1() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(),
                2
        );
        int[][] expect = {
                {},
                {}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void test2() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(),
                1
        );
        int[][] expect = {
                {}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void testConvert1() {
        ConvertList2Array convertList = new ConvertList2Array();

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});

        List<Integer> result = convertList.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(result, is(expect));
    }

    @Test
    public void testConvert2() {
        ConvertList2Array convertList = new ConvertList2Array();

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{});
        list.add(new int[]{3, 4, 5, 6});

        List<Integer> result = convertList.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(result, is(expect));
    }

    @Test
    public void testConvert3() {
        ConvertList2Array convertList = new ConvertList2Array();

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{});

        List<Integer> result = convertList.convert(list);
        List<Integer> expect = Arrays.asList();

        assertThat(result, is(expect));
    }
}