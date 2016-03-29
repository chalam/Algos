package datastructures.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueLinkedListTest {
    @Test
    public void useQueue() throws Exception {
        MyQueue que = new MyQueueLinkedList<String>();
        que.enQueue("one");
        que.enQueue("two");
        que.enQueue("three");
        que.enQueue("four");
        que.enQueue("five");
        System.out.println(que);

        assertEquals("Queue failed", "one", que.deQueue());
        assertEquals("Queue failed", "two", que.deQueue());
        assertEquals("Queue failed", "three", que.deQueue());

        System.out.println(que);

        assertEquals("Queue failed", 2, que.size());

        assertEquals("Queue failed", "four", que.deQueue());

        assertEquals("Queue failed", "five", que.peek());
        assertEquals("Queue failed", "five", que.deQueue());

        assertEquals("Queue failed", 0, que.size());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyDeQueue() {
        MyQueue que = new MyQueueLinkedList<String>();
        assertTrue(que.isEmpty());
        assertEquals("Queue failed", que.peek(), false);
        que.deQueue();
    }

}