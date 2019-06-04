package ru.job4j.tracker.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSingle2Test {
    
    @Test
    public void getInstance() {
        TrackerSingle2 instance1 = TrackerSingle2.getInstance();
        TrackerSingle2 instance2 = TrackerSingle2.getInstance();
    
        assertThat(instance1, is(instance2));
    }
}