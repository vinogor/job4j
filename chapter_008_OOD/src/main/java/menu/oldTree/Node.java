package menu.oldTree;

import java.util.*;

public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();
    private final E value;
    private final int level;

    public Node(final E value, final int level) {
        this.value = value;
        this.level = level;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return this.value;
    }

    public int getLevel() {
        return level;
    }
}