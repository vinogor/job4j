package list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
    }

    @Test
    public void pushAndPoll() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }
}