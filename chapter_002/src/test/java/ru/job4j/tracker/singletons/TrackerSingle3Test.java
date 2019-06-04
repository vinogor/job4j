package ru.job4j.tracker.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSingle3Test {
    
    @Test
    public void getInstance() {
        TrackerSingle3 instance1 = TrackerSingle3.getInstance();
        TrackerSingle3 instance2 = TrackerSingle3.getInstance();
        
        assertThat(instance1, is(instance2));
    }
}