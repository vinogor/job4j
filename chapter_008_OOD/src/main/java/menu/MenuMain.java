package menu;

public class MenuMain {

    private Point point0 = new Point("корень меню");
    private MenuTree menuTree = new MenuTree(point0);

    private void start() {
        fill();
        printAll();
        actAll();
    }

    private void fill() {

        Point point01 = new Point("задача 1");
        Point point02 = new Point("задача 2");
        Point point03 = new Point("задача 3");

        menuTree.add(point0, point02);
        menuTree.add(point0, point01);
        menuTree.add(point0, point03);

        Point point031 = new Point("задача 3.1");
        Point point0311 = new Point("задача 3.1.1");

        menuTree.add(point03, point031);
        menuTree.add(point031, point0311);

        Point point011 = new Point("задача 1.1");
        Point point013 = new Point("задача 1.3");
        Point point012 = new Point("задача 1.2");

        menuTree.add(point01, point011);
        menuTree.add(point01, point013);
        menuTree.add(point01, point012);

        Point point0111 = new Point("задача 1.1.1");
        Point point0112 = new Point("задача 1.1.1");

        menuTree.add(point011, point0111);
        menuTree.add(point011, point0112);

        Point point021 = new Point("задача 2.1");

        menuTree.add(point02, point021);
    }

    private void printAll() {
        for (Point point : menuTree) {
            System.out.println(point);
        }
    }

    private void actAll() {
        for (Point point : menuTree) {
            point.doSomeAction();
        }
    }

    public static void main(String[] args) {
        new MenuMain().start();
    }
}