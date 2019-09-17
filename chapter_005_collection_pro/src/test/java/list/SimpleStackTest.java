package list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();
    }

    @Test
    public void pushAndPoll() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}