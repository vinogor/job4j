package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyTreeTest {

    private MyTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new MyTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }

    @Test
    public void findByTest1() {
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void findByTest2() {
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void iteratorTest1() {
        Iterator<Integer> iterator = tree.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));

        assertThat(iterator.hasNext(), is(false));
    }
}