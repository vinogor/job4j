package list;

public class CheckCycle {
    Node first = new Node(1);
    Node two = new Node(2);
    Node third = new Node(3);
    Node four = new Node(4);

    class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public void setNodsNext(Node firstNext, Node twoNext, Node thirdNext, Node fourNext) {
        this.first.next = firstNext;
        this.two.next = twoNext;
        this.third.next = thirdNext;
        this.four.next = fourNext;
    }

    boolean hasCycle(Node first) {
        boolean result = false;
        Node currentNode = first;
        // либо уткнёмся в null, либо обнаружим совпадение ссылок
        while (!result) {
            if ((currentNode == null) || (currentNode.next == null)) {
                break;
            }
            currentNode = currentNode.next;
            // проходимся от начала до текущего
            Node searchNode = first;
            while (searchNode.next != currentNode) {
                if (currentNode.next == searchNode) {
                    result = true;
                    break;
                }
                searchNode = searchNode.next;
            }
        }
        return result;
    }
}