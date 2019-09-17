package set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<String> simpleSet;
    private static final String DUMMY_1 = "dummy 1";
    private static final String DUMMY_2 = "dummy 2";

    @Before
    public void setUp() {
        simpleSet = new SimpleSet<>();
    }

    @Test
    public void addTest1() {
        simpleSet.add(DUMMY_1);
        assertThat(simpleSet.size(), is(1));
    }

    @Test
    public void addTest2() {
        simpleSet.add(DUMMY_1);
        simpleSet.add(DUMMY_1);
        assertThat(simpleSet.size(), is(1));
    }

    @Test
    public void addTest3() {
        simpleSet.add(DUMMY_1);
        simpleSet.add(null);
        simpleSet.add(DUMMY_2);
        assertThat(simpleSet.size(), is(3));
    }

    @Test
    public void addTest4() {
        simpleSet.add(DUMMY_1);
        simpleSet.add(null);
        simpleSet.add(DUMMY_2);

        Iterator<String> iterator = simpleSet.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(DUMMY_1));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), nullValue());

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(DUMMY_2));

        assertThat(iterator.hasNext(), is(false));
    }
}