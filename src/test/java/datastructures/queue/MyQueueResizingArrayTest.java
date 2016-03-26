package datastructures.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/26/2016.
 */
public class MyQueueResizingArrayTest {

    @Test
    public void useQueue() throws Exception {
        MyQueue que = new MyQueueResizingArray<String>();
        que.enQueue("one");
        System.out.println(que);

        que.enQueue("two");
        System.out.println(que);

        que.enQueue("three");
        System.out.println(que);

        que.enQueue("four");
        System.out.println(que);

        que.enQueue("five");
        System.out.println(que);

        que.enQueue("six");
        System.out.println(que);

        que.enQueue("seven");
        System.out.println(que);

        assertEquals("Queue failed", que.deQueue(), "one");
        System.out.println(que);

        assertEquals("Queue failed", que.deQueue(), "two");
        System.out.println(que);

        assertEquals("Queue failed", que.deQueue(), "three");
        System.out.println(que);


        assertEquals("Queue failed", que.size(), 4);

        assertEquals("Queue failed", que.deQueue(), "four");
        System.out.println(que);

        assertEquals("Queue failed", que.peek(), "five");

        assertEquals("Queue failed", que.deQueue(), "five");
        System.out.println(que);

        assertEquals("Queue failed", que.size(), 2);
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyDeQueue() {
        MyQueue que = new MyQueueResizingArray<String>();
        assertTrue(que.isEmpty());
        assertEquals("Queue failed", que.peek(), false);
        que.deQueue();
    }

}