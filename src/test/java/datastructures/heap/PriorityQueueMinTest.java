package datastructures.heap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/28/2016.
 */
public class PriorityQueueMinTest {
    @Test
    public void doPriorityTest() {
        PriorityQueueMin<String> heap = new PriorityQueueMin<>(12);
        assertTrue("Heap should be empty", heap.isEmpty());
        heap.insert("P");
        assertFalse("Heap shouldn't be empty", heap.isEmpty());

        heap.insert("Q");
        heap.insert("E");
        assertEquals("Heap's max not right", "E", heap.deleteMax());
        heap.insert("X");
        heap.insert("A");
        heap.insert("M");

        assertEquals("Heap's max not right", "A", heap.min());
        assertEquals("Heap's max not right", "A", heap.deleteMax());
        assertEquals("Heap's size not right", 4, heap.size());

        heap.insert("P");
        heap.insert("L");
        heap.insert("E");
        assertEquals("Heap's max not right", "E", heap.deleteMax());
        assertEquals("Heap's size not right", 6, heap.size());
    }
}