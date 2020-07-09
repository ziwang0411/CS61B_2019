package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        assertEquals(1, (int) heap.getSmallest());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        assertEquals(1, (int) heap.getSmallest());
        assertTrue(heap.contains(1));
        assertFalse(heap.contains(6));
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        heap.removeSmallest();
        assertEquals(2, (int) heap.getSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i=1; i<6; i++) {
            heap.add(6-i, 6-i);
        }
        heap.changePriority(2, 0);
        assertEquals(2, (int) heap.getSmallest());
        heap.changePriority(1, 6);
        heap.removeSmallest();
        assertEquals(3, (int) heap.getSmallest());
    }

    @Test
    public void addTimeCompare() {

        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        int[] items =new int[100000];
        double[] prioritys = new double[100000];
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            items[i] = r.nextInt(100000);
            prioritys[i] = r.nextDouble()*100;
        }


        for (int i = 0; i < 100000; i++) {
            pq.add(items[i], prioritys[i]);
        }
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 100000; i++) {
            pq.removeSmallest();
        }
        System.out.println(sw.elapsedTime() + " s for removing ArrayHeapMinPQ 1000");


        NaiveMinPQ<Integer> pq2 = new NaiveMinPQ<>();

        for (int i = 0; i < 100000; i++) {
            pq2.add(items[i], prioritys[i]);
        }
        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < 100000; i++) {
            pq2.removeSmallest();
        }
        System.out.println(sw2.elapsedTime() + " s for removing NaiveMinPQ 1000");

    }
}
