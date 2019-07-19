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
    public void poll() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void push() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.get(0), is(3));
        assertThat(stack.get(1), is(2));
        assertThat(stack.get(2), is(1));
    }
}