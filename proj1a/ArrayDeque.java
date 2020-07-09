import java.util.Objects;

public class ArrayDeque<T> {
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
        return (index - 1) % items.length;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFactor);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFactor);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }
    public boolean isEmpty() {
        return (size ==0);
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.println(items[curr] + " ");
            curr = plusOne(curr);
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -=1;

        if (items.length >= size*RFactor && size < items.length * Min_Usage_Ratio) {
            resize(items.length / RFactor);
        }
        return result;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -=1;

        if (items.length >= size*RFactor && size < items.length * Min_Usage_Ratio) {
            resize(items.length / RFactor);
        }

        return result;
    }
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
        for (int i = 0; i< other.size; i++) {
            addLast((T) other.get(i));
        }

    }


}
