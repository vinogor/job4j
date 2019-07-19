package list;

import org.junit.Test;
import org.junit.Before;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void deleteTest01() {
        Integer result = list.delete();
        assertThat(result, is(3));
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteTest02() {
        list.delete();
        list.delete();
        list.delete();
        list.delete();
    }
}