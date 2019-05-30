package ru.job4j.pseudo;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String nextLine = System.lineSeparator();
        pic.append("   +   ").append(nextLine);
        pic.append("  + +  ").append(nextLine);
        pic.append(" +   + ").append(nextLine);
        pic.append("+++++++").append(nextLine);
        return pic.toString();
    }
}