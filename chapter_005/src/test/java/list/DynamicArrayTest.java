package list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class DynamicArrayTest {

    private DynamicArray<String> simpleArray;
    private static final String DUMMY_1 = "dummy 1";
    private static final String DUMMY_2 = "dummy 2";
    private static final String DUMMY_3 = "dummy 3";
    private static final String DUMMY_MOD = "dummy mod";

    @Before
    public void setUp() {
        simpleArray = new DynamicArray<>(2);
    }

    @Test
    public void addTest1() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        assertThat(simpleArray.get(1), is(DUMMY_2));
    }

    @Test
    public void addTest2() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        assertThat(simpleArray.size(), is(2));
        simpleArray.add(DUMMY_3);
        assertThat(simpleArray.size(), is(4));
    }

    @Test
    public void setTest1() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        simpleArray.set(1, DUMMY_MOD);
        assertThat(simpleArray.get(1), is(DUMMY_MOD));
    }

    @Test
    public void removeTest1() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        simpleArray.remove(0);
        assertThat(simpleArray.get(0), is(DUMMY_2));
    }

    @Test
    public void getTest1() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        assertThat(simpleArray.get(0), is(DUMMY_1));
        assertThat(simpleArray.get(1), is(DUMMY_2));
    }

    @Test
    public void iteratorTest1() {
        simpleArray.add(DUMMY_1);
        simpleArray.add(DUMMY_2);
        Iterator<String> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(DUMMY_1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(DUMMY_2));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorTest2() {
        simpleArray.add(DUMMY_1);
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
        iterator.next();
    }
}