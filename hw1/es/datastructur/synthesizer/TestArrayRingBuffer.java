package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        for (int i = 0; i<10; i++) {arb.enqueue(i);}
        assertTrue(arb.isFull());
        assertEquals(0,arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals(1, arb.peek());
        arb.enqueue(11);
        assertTrue(arb.isFull());



    }
}
