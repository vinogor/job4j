package ru.job4j.tracker.singletons;

public class ManualTests {
    
    public static void main(String[] args) {
        String name1 = TrackerSingle1.INSTANCE_ENUM_1.name();
        System.out.println(name1);
        TrackerSingle1.INSTANCE_ENUM_1.enumMethod();

        // тут вылезет ошибка если раскомментить
        // TrackerSingle2 a = new TrackerSingle2();
        
        int i2 = TrackerSingle2.getInstance().getI();
        System.out.println("Lazy singleton, i = " + i2);
    
        int i3 = TrackerSingle3.getInstance().getI();
        System.out.println("Eager singleton, i = " + i3);
    
        int i4 = TrackerSingle4.getInstance().getI();
        System.out.println("Eager singleton, i = " + i4);
        
    }
}
