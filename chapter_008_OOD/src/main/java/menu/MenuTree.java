package menu;

import menu.oldTree.MyTree;
import menu.oldTree.Node;

import java.util.*;

public class MenuTree extends MyTree<Point> {

    private final Node<Point> root;

    public MenuTree(Point root) {
        super(root);
        this.root = super.getRoot();
    }

    // в переопределённом методе добавим новый функционал:
    // 1) переделаем в проход в глубину из прохода в ширину
    // 2) отсортируем листья по полю name при получении листьев от одного узла
    // 3) проставим в Point level взятый из узла
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {

            int tempModCounter = MenuTree.super.getModCounter();
            Stack<Node<Point>> tempData = new Stack<>();

            {
                tempData.add(root);
            }

            @Override
            public boolean hasNext() {
                if (tempModCounter != MenuTree.super.getModCounter()) {
                    throw new ConcurrentModificationException();
                }
                return !tempData.isEmpty();
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<Point> leaf = tempData.pop();
                List<Node<Point>> children = leaf.leaves();

                children.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                for (Node<Point> node : children) {
                    tempData.push(node);
                }
                Point result = leaf.getValue();
                result.setLevel(leaf.getLevel());
                return result;
            }
        };
    }
}