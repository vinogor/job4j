package ru.job4j.tracker.singletons;

/**
 * 3)
 * Singleton from static final field.
 * Eager loading.
 */
public class TrackerSingle3 {
    
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();
    
    private int i = 333;
    
    private TrackerSingle3() {
        System.out.println("private constructor");
    }
    
    public static TrackerSingle3 getInstance() {
        return INSTANCE;
    }
    
    public int getI() {
        return i;
    }
}