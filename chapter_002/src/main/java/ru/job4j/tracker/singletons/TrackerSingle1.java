package ru.job4j.tracker.singletons;

/**
 * 1)
 * Singleton from Enum.
 * Eager loading.
 */
public enum TrackerSingle1 {
    INSTANCE_ENUM_1;

    public  int i = this.toString().length();
    
    public void enumMethod() {
        System.out.println("this is enum method! Instance name = " + this.name() + ", i = " + i);
    }
    
    public static TrackerSingle1 getInstance() {
        return INSTANCE_ENUM_1;
    }
}