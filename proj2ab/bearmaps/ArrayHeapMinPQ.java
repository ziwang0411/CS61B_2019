package bearmaps;

import java.util.*;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> heapArray;
    private int nextIndex;
    private Map<T, PriorityNode> itemmap;

    /*
    heap example:
    0   1   2   3   4   5   6   7
    -   3   5   9   15  17  19  21      with only PriorityNode's item shown
     */

    public ArrayHeapMinPQ() {
        heapArray = new ArrayList<>();
        itemmap = new HashMap<>();
        heapArray.add(null);
        nextIndex = 1;
    }

    @Override
    public void add(T item, double priority) {
        PriorityNode cur = new PriorityNode(item,priority,nextIndex);
        heapArray.add(cur);
        itemmap.put(item,cur);
        nextIndex +=1;
        swim(nextIndex-1);
    }

    private void swim(int childPos) {
        int parentPos = childPos /2;
        if (parentPos ==0) return;
        int cmp = heapArray.get(childPos).compareTo(heapArray.get(parentPos));
        if (cmp < 0) {
            swap(childPos,parentPos);
            swim(parentPos);
        }
    }

    private void swap(int i, int j) {
        PriorityNode iNode = heapArray.get(i);
        PriorityNode jNode = heapArray.get(j);
        heapArray.set(i, jNode);
        heapArray.set(j, iNode);
        jNode.setIndex(i);
        iNode.setIndex(j);
    }

    @Override
    public boolean contains(T item) {
        return itemmap.containsKey(item);
    }

    @Override
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (nextIndex ==1) {throw new NoSuchElementException("no elements in the PQ");
        }
        return heapArray.get(1).getItem();
    }

    @Override
    public T removeSmallest() {

        if (nextIndex ==1) {throw new NoSuchElementException("no elements in the PQ");}
        T smallest = heapArray.get(1).getItem();
        swap(1,nextIndex-1);
        nextIndex -=1;
        heapArray.remove(nextIndex);
        itemmap.remove(smallest);
        sink(1);
        return smallest;
    }

    private void sink(int parentPos) {

        int leftchildPos = parentPos*2;
        int rightchildPos = parentPos*2+1;
        if (leftchildPos > nextIndex-1) return; //leaf
        if (leftchildPos == nextIndex-1) {//left child only
            int cmp = heapArray.get(parentPos).compareTo(heapArray.get(leftchildPos));
            if (cmp > 0) {
                swap(parentPos, leftchildPos);}

        } //left child only
        else {
            int cmp = heapArray.get(leftchildPos).compareTo(heapArray.get(rightchildPos));
            if (cmp < 0) {
                {if (heapArray.get(parentPos).compareTo(heapArray.get(leftchildPos)) > 0) {
                    swap(parentPos, leftchildPos);
                    sink(leftchildPos);
                }
                }
            } else {if (heapArray.get(parentPos).compareTo(heapArray.get(rightchildPos)) > 0) {
                swap(parentPos, rightchildPos);
                sink(rightchildPos);
                }
            }
        }

/*
        while (2*parentPos < nextIndex) {
            int j = 2*parentPos;
            if (j < nextIndex-1 && greater(j, j+1)) j+=1;
            if (!greater(parentPos, j)) break;
            swap(parentPos, j);
            parentPos = j;
        }
*/
    }

    private boolean greater(int j, int i) {
        return heapArray.get(i).compareTo(heapArray.get(j)) > 0;
    }

    @Override
    public int size() {
        return nextIndex-1;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) throw new NoSuchElementException();
        PriorityNode tmp = itemmap.get(item);
        tmp.setPriority(priority);
        int k = tmp.getIndex();
        swim(k);
        sink(k);

    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;
        private int index;

        PriorityNode(T e, double p, int i) {
            this.item = e;
            this.priority = p;
            this.index = i;
        }

        T getItem() {
            return item;
        }
        public int getIndex() {
            return index;
        }

        void setIndex(int x) {
            this.index = x;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public static void printItem(ArrayHeapMinPQ<Integer> pq) {
        int size = pq.heapArray.size();
        Object[] heapItem = new Object[size];
        int heapItemsize = heapItem.length;
        for (int i =1; i < size; i++) {
            heapItem[i] = pq.heapArray.get(i).getItem();
        }
        System.out.println("----------------");
        PrintHeapDemo.printSimpleHeapDrawing(heapItem);
    }

    public static void printIndex(ArrayHeapMinPQ<Integer> pq) {
        int size = pq.heapArray.size();
        Object[] heapItem = new Object[size];
        int heapItemsize = heapItem.length;
        for (int i =1; i < size; i++) {
            heapItem[i] = pq.heapArray.get(i).getIndex();
        }
        System.out.println("----------------");
        PrintHeapDemo.printSimpleHeapDrawing(heapItem);
    }
    public static void main(String[] args) {
/*
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(10, 1);
        pq.add(20, 2);
        pq.add(30, 3);
        pq.add(40, 4);
        printItem(pq);
        pq.removeSmallest();
        printItem(pq);
        pq.changePriority(40,1);
        printItem(pq);
*/
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.add(10, 1);
        pq.add(20, 2);
        pq.add(30, 3);
        pq.add(40, 4);
        pq.add(50, 5);
        pq.add(60, 6);
        pq.add(70, 7);
        pq.add(80, 8);
        pq.add(90,9);
        printItem(pq);
        printIndex(pq);
        pq.changePriority(40,1.1);
        printItem(pq);
        pq.removeSmallest();
        printItem(pq);
        pq.removeSmallest();
        printItem(pq);
        pq.removeSmallest();
        printItem(pq);
        pq.removeSmallest();
        printItem(pq);
    }
}
