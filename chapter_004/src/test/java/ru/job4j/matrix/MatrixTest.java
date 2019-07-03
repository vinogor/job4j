package ru.job4j.matrix;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MatrixTest {

    private static Matrix matrix;

    @Before
    public void setUp()  {
        matrix = new Matrix();
    }

    @Test
    public void convertTest1() {
        Integer[][] matrix2x2 = new Integer[][] {
                {1, 2},
                {3, 4}
        };

        List<Integer> result = matrix.convert(matrix2x2);
        assertThat(result, is(List.of(1,2,3,4)));
    }
}