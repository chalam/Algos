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

        assertEquals("Queue failed", que.deQueue(), "one");

        assertEquals("Queue failed", que.deQueue(), "two");
        assertEquals("Queue failed", que.deQueue(), "three");
        System.out.println(que);


        assertEquals("Queue failed", que.size(), 2);

        assertEquals("Queue failed", que.deQueue(), "four");
        assertEquals("Queue failed", que.peek(), "five");

        assertEquals("Queue failed", que.deQueue(), "five");
        assertEquals("Queue failed", que.size(), 0);
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyDeQueue() {
        MyQueue que = new MyQueueLinkedList<String>();
        assertTrue(que.isEmpty());
        assertEquals("Queue failed", que.peek(), false);
        que.deQueue();
    }

}