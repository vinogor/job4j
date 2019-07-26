package map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private Map<User, Object> map;

    void doSmthng() {
        User user1 = new User("Sasha", 2, new GregorianCalendar());
        User user2 = new User("Sasha", 2, new GregorianCalendar());
        map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        print();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.doSmthng();
    }

    void print() {
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Item №" + i++);
            System.out.println("    Key = " + entry.getKey());
            System.out.println("    Value = " + entry.getValue());
        }
    }
}