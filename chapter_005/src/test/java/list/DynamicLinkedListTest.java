package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
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

    @Test()
    public void iteratorTest01() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorTest02() {
        Iterator<Integer> iterator = list.iterator();
        list.delete();
        iterator.hasNext();
    }
}