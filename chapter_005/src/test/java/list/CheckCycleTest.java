package list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckCycleTest {

    private CheckCycle checkCycle;
    private Node first;
    private Node two;
    private Node third;
    private Node four;

    @Before
    public void setUp() {
        checkCycle = new CheckCycle();
        first = new Node(1);
        two = new Node(2);
        third = new Node(3);
        four = new Node(4);
    }

    // кольцо есть, из first оно достигается
    @Test
    public void hasCycleTest1() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        assertThat(checkCycle.hasCycle(first), is(true));
    }

    // кольцо есть, из first оно НЕ достигается
    @Test
    public void hasCycleTest2() {
        first.next = two;
        two.next = null;
        third.next = four;
        four.next = third;

        assertThat(checkCycle.hasCycle(first), is(false));
    }

    // кольца НЕТ вообще
    @Test
    public void hasCycleTest3() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        assertThat(checkCycle.hasCycle(first), is(false));
    }
}