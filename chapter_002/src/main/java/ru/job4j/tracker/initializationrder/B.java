package ru.job4j.tracker.initializationrder;

public class B extends A {
    
    public static void main(String[] args) {
        System.out.println("запускается только статика, так как объект не создан");
    }
    
    private int b = bb(20);
    
    public B(int b) {
        this.b = b;
    }
    
    private int bb(int x) {
        System.out.println("B class. No static perem " + x);
        return 0;
    }
    
    public B() {
        System.out.println("B class. Constructor");
    }
    
    static {
        System.out.println("B class. Static block 1");
    }
}