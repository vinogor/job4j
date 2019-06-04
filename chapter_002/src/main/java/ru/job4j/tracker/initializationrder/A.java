package ru.job4j.tracker.initializationrder;

public class A {
    
    {
        System.out.println("A class. No static block 1");
    }
    
    {
        System.out.println("A class. No static block 2");
    }
    
    {
        System.out.println("A class. No static block 3");
    }
    
    static {
        System.out.println("A class. Static block 1");
        
    }
    
    static {
        System.out.println("A class. Static block 2");
        
    }
   
    public static String a1 = psv("a1");
    
    
    public static final String F1 = psv("FINAL f1");
    
    
    public String a11 = pnsv("a11");
    
    public final String f11 = pnsv("FINAL f11");
    
    
    static {
        System.out.println("A class. Static block 3");
        
    }
    public static String a2 = psv("a2");
    
    private static String psv(String a) {
        System.out.println("A class. Static perem " + a);
        return a;
    }
    
    private String pnsv(String a) {
        System.out.println("A class. No static perem " + a);
        return a;
        
    }
    
    public A() {
        System.out.println("A class. Constructor");
    }
    
    
    public String a22 = pnsv("a22");
    
    public static String a3 = psv("a3");
    
    {
        System.out.println("A class. No static block 4");
    }
    
    public String a33 = pnsv("a33");
    
}