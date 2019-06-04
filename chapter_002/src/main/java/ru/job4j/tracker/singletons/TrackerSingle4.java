package ru.job4j.tracker.singletons;

/**
 * 4)
 * Singleton from static final field.
 * Lazy loading.
 */
public class TrackerSingle4 {
    
    private static final class Holder {
        private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
    }
    
    private int i = 444;
    
    private TrackerSingle4() {
        System.out.println("private constructor");
    }
    
    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }
    
    public int getI() {
        return i;
    }
}
