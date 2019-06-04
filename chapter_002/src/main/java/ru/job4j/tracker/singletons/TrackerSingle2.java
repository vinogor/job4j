package ru.job4j.tracker.singletons;

/**
 * 2)
 * Singleton from static field.
 * Lazy loading.
 */
public class TrackerSingle2 {

    private static TrackerSingle2 instance;
    
    private int i = 222;
    
    private TrackerSingle2() {
        System.out.println("private constructor");
    }
    
    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            System.out.println("instance = " + instance);
            System.out.println("creating instance");
            instance = new TrackerSingle2();
            System.out.println("instance = " + instance);
        }
        return instance;
    }
    
    public int getI() {
        return i;
    }
}