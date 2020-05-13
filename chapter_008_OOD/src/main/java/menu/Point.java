package menu;

public class Point implements Comparable<Point>, PointAction {

    private int level;
    private String name;

    public Point(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Point o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ".repeat(Math.max(0, level)));
        sb.append(name);
        return sb.toString();
    }

    @Override
    public void doSomeAction() {
        System.out.println("действие для пункта меню " + name);
    }
}