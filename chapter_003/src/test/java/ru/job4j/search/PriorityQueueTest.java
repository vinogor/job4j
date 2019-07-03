package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority1() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.takeFirst();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriority2() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 10));
        queue.put(new Task("middle", 3));
        var result = queue.takeFirst();
        assertThat(result.getDesc(), is("middle"));
    }

    @Test
    public void whenHigherPriority3() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 10));
        queue.put(new Task("urgent", 10));
        queue.put(new Task("middle", 10));
        var result = queue.takeFirst();
        assertThat(result.getDesc(), is("middle"));
    }

    @Test
    public void whenHigherPriority4() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("lowlow", 8));
        var result = queue.takeLast();
        assertThat(result.getDesc(), is("lowlow"));
    }
}