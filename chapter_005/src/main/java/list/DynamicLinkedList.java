package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private int modCount = 0;
    Node<E> nodeForIterator;


    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first; // новая указывает на первую в списке
        this.first = newLink; // новая становится первой в списке (старая первая становится второй)
        this.size++;
        this.modCount++;
    }

    public E delete() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E result = first.data;
        first = first.next;
        size--;
        this.modCount++;
        return result;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        nodeForIterator = first;

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nodeForIterator.next != null;
            }

            @Override
            public E next() {
                E result;
                if (hasNext()) {
                    result = nodeForIterator.data;
                    nodeForIterator = nodeForIterator.next;
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}