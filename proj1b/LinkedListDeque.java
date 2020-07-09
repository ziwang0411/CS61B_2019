public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinal;
    private int size;

    private class Node {
        Node prev;
        T item;
        Node next;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /**
     * create an empty deque
     */
    public LinkedListDeque() {
        sentinal = new Node(null, null, null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        size = 0;
    }
    @Override
    public void addLast(T item) {
        Node temp = new Node(sentinal.prev, item, sentinal);
        sentinal.prev.next = temp;
        sentinal.prev = temp;
        size += 1;
    }
    @Override
    public void addFirst(T item) {
        Node temp = new Node(sentinal, item, sentinal.next);
        sentinal.next.prev = temp;
        sentinal.next = temp;
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        if (sentinal.prev.equals(sentinal) && sentinal.next.equals(sentinal) && (size == 0)) {
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        Node ptr = sentinal.next;
        while (ptr != sentinal) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.next.prev = sentinal;
        size -= 1;
        return result;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size -= 1;
        return result;
    }
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node ptr = sentinal.next;
        while (index > 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.item;
    }


    public LinkedListDeque(LinkedListDeque other) {
        sentinal = new Node(null, null, null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }

    }


//    public static void main(String[] args) {
//        LinkedListDeque a = new LinkedListDeque();
//        a.addLast(1);
//        a.addLast(2);
//        a.addLast(3);
//        a.printDeque();
//        a.removeFirst();
//        a.printDeque();
//    }

}