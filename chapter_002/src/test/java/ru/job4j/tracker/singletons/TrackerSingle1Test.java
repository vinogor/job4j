package ru.job4j.tracker.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSingle1Test {
    
    @Test
    public void getInstance() {
        TrackerSingle1 instance1 = TrackerSingle1.getInstance();
        TrackerSingle1 instance2 = TrackerSingle1.getInstance();
    
        assertThat(instance1, is(instance2));
    }
}