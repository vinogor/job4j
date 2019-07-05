import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StreamExamTest {

    private StreamExam streamExam;

    @Before
    public void setUp() {
        streamExam = new StreamExam();
    }

    @Test
    public void filterArrayTest1() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int result = streamExam.filterArray(array);
        assertThat(result, is(20));
    }

    @Test
    public void filterArrayTest2() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        int result = streamExam.filterArray(array);
        assertThat(result, is(56));
    }

    @Test
    public void filterArrayTest3() {
        int[] array = new int[]{1};
        int result = streamExam.filterArray(array);
        assertThat(result, is(0));
    }
}