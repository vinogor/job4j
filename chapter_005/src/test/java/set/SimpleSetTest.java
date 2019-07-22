package set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<String> simpleSet;
    private static final String DUMMY_1 = "dummy 1";

    @Before
    public void setUp() {
        simpleSet = new SimpleSet<>();
    }

    @Test
    public void addTest1() {
        simpleSet.add(DUMMY_1);
        assertThat(simpleSet.size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTest2() {
        simpleSet.add(DUMMY_1);
        simpleSet.add(DUMMY_1);
    }

    @Test
    public void iterator() {
    }
}