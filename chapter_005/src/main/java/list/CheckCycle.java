package list;

public class CheckCycle {

    private DynamicLinkedList<Node> list = new DynamicLinkedList<>();

    boolean hasCycle(Node first) {
        boolean result = false;
        list.add(first);
        Node next = first.next;
        // либо уткнёмся в null, либо обнаружим совпадение ссылок
        while (next != null) {
            for (int i = 0; i < list.getSize(); i++) {
                if (next == list.get(i)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
            list.add(next);
            next = next.next;
        }
        return result;
    }
}