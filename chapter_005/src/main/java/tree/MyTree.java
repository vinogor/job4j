package tree;

import java.util.*;

public class MyTree<E extends Comparable<E>> implements SimpleTree<E> {

    private final Node<E> root;

    private int counter = 0;

    public MyTree(E e) {
        this.root = new Node<>(e);
        counter++;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> optionalNodeParent = findBy(parent);
        if (optionalNodeParent.isPresent()) {
            Node<E> nodeParent = optionalNodeParent.get();
            List<Node<E>> leaves = nodeParent.leaves();
            // если дублей нет, то добавляем
            if (!hasSameNode(leaves, child)) {
                nodeParent.add(new Node<>(child));
                counter++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root); // почему именно offer ??
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int tempCounter = 0;

            // преобразуем дерево в связный список
            Queue<Node<E>> tempData1 = new LinkedList<>();
            Queue<Node<E>> tempData2 = new LinkedList<>();

            {
                tempData1.offer(root);
                while (!tempData1.isEmpty()) {

                    Node<E> tempNode = tempData1.poll(); // получаем и удаляем
                    tempData2.add(tempNode);

                    for (Node<E> child : tempNode.leaves()) {
                        tempData1.offer(child);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return tempCounter != counter;
            }

            @Override
            public E next() {
                E result = null;
                if (hasNext()) {
                    tempCounter++;
                    Node<E> poll = tempData2.poll();
                    result = poll.getValue();
                }
                return result;
            }
        };
    }

    private boolean hasSameNode(List<Node<E>> leaves, E child) {
        boolean result = false;
        for (Node node : leaves) {
            if (node.eqValue(child)) {
                result = true;
            }
        }
        return result;
    }
}