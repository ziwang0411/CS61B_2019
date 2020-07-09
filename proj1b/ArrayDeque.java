import java.util.Objects;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    int nextFirst;
    int nextLast;
    private static final int RFactor = 2;
    private static final int eight = 8;
    private static final double Min_Usage_Ratio = 0.25;


    public ArrayDeque() {
        items = (T[]) new Object[eight];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[curr];
            curr = plusOne(curr);
        }

        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFactor);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFactor);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.println(items[curr] + " ");
            curr = plusOne(curr);
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;

        if (items.length >= size * RFactor && size < items.length * Min_Usage_Ratio) {
            resize(items.length / RFactor);
        }
        return result;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = minusOne(nextLast);
        T result = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;

        if (items.length >= size * RFactor && size < items.length * Min_Usage_Ratio) {
            resize(items.length / RFactor);
        }

        return result;
    }
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int target = (nextFirst + index + 1) % items.length;
        return items[target];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[eight];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }

    }


}
