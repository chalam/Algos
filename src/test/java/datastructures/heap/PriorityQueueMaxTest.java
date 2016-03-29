package datastructures.heap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/28/2016.
 */
public class PriorityQueueMaxTest {
    @Test
    public void doPriorityTest() {
        PriorityQueueMax<String> heap = new PriorityQueueMax<>(12);
        assertTrue("Heap should be empty", heap.isEmpty());
        heap.insert("P");
        assertFalse("Heap shouldn't be empty", heap.isEmpty());

        heap.insert("Q");
        heap.insert("E");
        assertEquals("Heap's max not right", "Q", heap.deleteMax());
        heap.insert("X");
        heap.insert("A");
        heap.insert("M");

        assertEquals("Heap's max not right", "X", heap.max());
        assertEquals("Heap's max not right", "X", heap.deleteMax());
        assertEquals("Heap's size not right", 4, heap.size());

        heap.insert("P");
        heap.insert("L");
        heap.insert("E");
        assertEquals("Heap's max not right", "P", heap.deleteMax());
        assertEquals("Heap's size not right", 6, heap.size());
    }

}