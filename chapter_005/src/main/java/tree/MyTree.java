package tree;

import java.util.*;

public class MyTree<E extends Comparable<E>> implements SimpleTree<E> {

    private final Node<E> root;
    private int counter = 0;
    private int modCounter = 0;

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
                modCounter++;
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
    public boolean isBinary() {
        // метод должен циклически пройти по всем элементам дерева.
        //  Для этого можно использовать итератор из предыдущего задания.
        boolean result = true;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E value = iterator.next(); // возвращал бы итератор узел а не значение, то было бы удобнее...
            Node<E> node = findBy(value).get();
            if (node.leaves().size() > 2) {
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int tempModCounter = modCounter;
            Queue<Node<E>> tempData = new LinkedList<>();
            {
                tempData.add(root);
            }

            @Override
            public boolean hasNext() {
                if (tempModCounter != modCounter) {
                    throw new ConcurrentModificationException();
                }
                return !tempData.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> leaf = tempData.poll();
                List<Node<E>> childrens = leaf.leaves();
                for (Node<E> node : childrens) {
                    tempData.offer(node);
                }
                return leaf.getValue();
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