package ru.job4j.tracker.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSingle4Test {
    
    @Test
    public void getInstance() {
        TrackerSingle4 instance1 = TrackerSingle4.getInstance();
        TrackerSingle4 instance2 = TrackerSingle4.getInstance();
        
        assertThat(instance1, is(instance2));
    }
}